package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public List<Vehicle> findByCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE customer_id = ? ORDER BY id";
        List<Vehicle> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) result.add(map(rs));
            }
        }
        return result;
    }

    private Vehicle map(ResultSet rs) throws SQLException {
        Vehicle v = new Vehicle();
        v.setId(rs.getInt("id"));
        v.setCustomerId(rs.getInt("customer_id"));
        v.setPlate(rs.getString("plate"));
        v.setMake(rs.getString("make"));
        v.setModel(rs.getString("model"));
        v.setColour(rs.getString("colour"));
        return v;
    }
}

