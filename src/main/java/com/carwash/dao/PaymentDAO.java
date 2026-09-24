package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Method;
import com.carwash.model.Payment;
import com.carwash.model.PaymentStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public int insert(Payment p) throws SQLException {
        String sql = "INSERT INTO payments (ticket_id, amount, method, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getTicketId());
            ps.setBigDecimal(2, p.getAmount());
            ps.setString(3, p.getMethod().name());
            ps.setString(4, p.getStatus() == null ? "COMPLETED" : p.getStatus().name());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public List<Payment> findAll() throws SQLException {
        String sql = "SELECT * FROM payments ORDER BY paid_at DESC";
        List<Payment> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(map(rs));
        }
        return result;
    }

    /**
    * total rev between two dates
    * */
    public BigDecimal totalRevenueBetween(LocalDate from, LocalDate to) throws SQLException {
        String sql = "SELECT COALESCE(SUM(amount), 0) AS total FROM payments " +
                "WHERE status = 'COMPLETED' AND DATE(paid_at) BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal("total");
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * rev grouped by day for the last x number of days
     * */
    public List<Object[]> revenueByDay(int days) throws SQLException {
        String sql = "SELECT DATE(paid_at) AS d, SUM(amount) AS total FROM payments " +
                "WHERE status = 'COMPLETED' AND paid_at >= DATE_SUB(CURRENT_DATE, INTERVAL ? DAY) " +
                "GROUP BY DATE(paid_at) ORDER BY d";

        List<Object[]> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                 ps.setInt(1, days);
                 try (ResultSet rs = ps.executeQuery()) {
                     while (rs.next()) {
                         result.add(new Object[]{rs.getDate("d").toLocalDate(), rs.getBigDecimal("total")});
                     }
                 }
        }
        return result;
    }

    /**
     * keep track of total customers by how much spent
     * */
    public List<Object[]> topCustomers(int limit) throws SQLException {
        String sql = "SELECT c.name, SUM(p.amount) AS total, COUNT(*) AS visits " +
                "FROM payments p " +
                "JOIN tickets t ON p.ticket_id = t.id " +
                "JOIN customers c ON t.customer_id = c.id " +
                "WHERE p.status = 'COMPLETED' " +
                "GROUP BY c.id, c.name ORDER BY total DESC LIMIT ?";
        List<Object[]> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, limit);
            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next())
                    result.add(new Object[]{rs.getString("name"), rs.getBigDecimal("total"), rs.getInt("visits")});
            }
        }
        return result;
    }


    private Payment map(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setId(rs.getInt("id"));
        p.setTicketId(rs.getInt("ticket_id"));
        p.setAmount(rs.getBigDecimal("amount"));
        p.setMethod(Method.valueOf(rs.getString("method")));
        p.setStatus(PaymentStatus.valueOf(rs.getString("status")));

        Timestamp ts = rs.getTimestamp("paid_at");
        p.setPaidAt(ts != null ? ts.toLocalDateTime() : null);
        return p;
    }

}
