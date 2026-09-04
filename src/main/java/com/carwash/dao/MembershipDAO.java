package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Membership;

import java.sql.*;

public class MembershipDAO {

    public Membership findActiveByCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM memberships WHERE customer_id = ? AND active = TRUE LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    private Membership map(ResultSet rs) throws SQLException {
        Membership m = new Membership();
        m.setId(rs.getInt("id"));
        m.setCustomerId(rs.getInt("customer_id"));
        m.setPlan(rs.getString("plan"));
        m.setMonthlyFee(rs.getBigDecimal("monthly_price"));
        m.setActive(rs.getBoolean("active"));

        Date d = rs.getDate("renews");
        m.setRenewing(d != null ? d.toLocalDate() : null);

        return m;
    }

}
