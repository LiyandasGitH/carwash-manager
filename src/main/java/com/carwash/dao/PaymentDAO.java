package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Method;
import com.carwash.model.Payment;
import com.carwash.model.PaymentStatus;

import java.sql.*;
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

    private Payment map(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setId(rs.getInt("id"));
        p.setTicketId(rs.getInt("ticket_id"));
        p.setAmount(rs.getBigDecimal("amount"));
        p.setMethod(Method.valueOf(rs.getString("method")));
        p.setStatus(PaymentStatus.valueOf(rs.getString("status")));

        Timestamp ts = rs.getTimestamp("paid_at");
        p.setPaid(ts != null ? ts.toLocalDateTime() : null);
        return p;
    }





}
