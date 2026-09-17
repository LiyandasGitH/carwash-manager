package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Ticket;
import com.carwash.model.TicketStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private static final String JOIN_SELECT =
            "SELECT t.*, c.name AS customer_name, \" +\n" +
            "            \"       CONCAT(v.plate, ' - ', v.make, ' ', v.model) AS vehicle_label, \" +\n" +
            "            \"       s.name AS service_name, e.name AS employee_name \" +\n" +
            "            \"FROM tickets t \" +\n" +
            "            \"JOIN customers c ON t.customer_id = c.id \" +\n" +
            "            \"JOIN vehicles v ON t.vehicle_id = v.id \" +\n" +
            "            \"JOIN services s ON t.service_id = s.id \" +\n" +
            "            \"LEFT JOIN employees e ON t.employee_id = e.id \n";

    public List<Ticket> findActive() throws SQLException {
        String sql = JOIN_SELECT + "WHERE t.status IN ('QEUED', 'IN_PROGRESS', 'DONE') ORDER BY t.created";
        return query(sql);
    }

    public List<Ticket> findAll() throws SQLException {
        String sql = JOIN_SELECT + "ORDER BY t.created_at DESC";
        return query(sql);
    }

    public Ticket findById(int id) throws SQLException {
        String sql = JOIN_SELECT + "WHERE t.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    private List<Ticket> query(String sql) throws SQLException {
        List<Ticket> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet res = prep.executeQuery()) {
            while (res.next())
                result.add(map(res));
        }
        return result;
    }

    public int insert(Ticket t) throws SQLException {
        String sql = "INSERT INTO tickets (customer_id, vehicle_id, service_id, employee_id, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prep.setInt(1, t.getCustomerId());
            prep.setInt(2, t.getVehicleId());
            prep.setInt(3, t.getServiceId());
            if (t.getEmployeeId() != null)
                prep.setInt(4, t.getEmployeeId());
            else prep.setNull(4, Types.INTEGER);
            prep.setString(5, t.getStatus() == null ? "QUEUED" : t.getStatus().name());
            prep.executeUpdate();
            try (ResultSet keys = prep.getGeneratedKeys()) {
                if (keys.next())
                    return keys.getInt(1);
            }
        }
        return 1;
    }

    private Ticket map(ResultSet rs) throws SQLException {
        Ticket t = new Ticket();
        t.setId(rs.getInt("id"));
        t.setCustomerId(rs.getInt("customer_id"));
        t.setVehicleId(rs.getInt("vehicle_id"));
        t.setServiceId(rs.getInt("service_id"));
        int empId = rs.getInt("employee_id");
        t.setEmployeeId(rs.wasNull() ? null : empId);
        t.setStatus(TicketStatus.valueOf(rs.getString("status")));

        Timestamp ts = rs.getTimestamp("created");
        t.setCreated(ts != null ? ts.toLocalDateTime() : null);
        t.setCustomerName(rs.getString("customer_name"));
        t.setVehicleLabel(rs.getString("vehicle_label"));
        t.setServiceName(rs.getString("service_name"));
        t.setEmployeeName(rs.getString("employee_name"));
        return t;
    }
}

