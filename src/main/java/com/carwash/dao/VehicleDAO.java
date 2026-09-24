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
             PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, customerId);
            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next())
                    result.add(map(rs));
            }
        }
        return result;
    }

    public List<Vehicle> findAll() throws SQLException {
        String sql = "SELECT * FROM vehicles ORDER BY id";
        return query(sql);
    }

    public Vehicle findByInt(int id) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return map(rs);
            }
        }
        return null;
    }

    private List<Vehicle> query(String sql) throws SQLException {
        List<Vehicle> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql);
        ResultSet rs = prep.executeQuery()) {
            while (rs.next())
                result.add(map(rs));
        }
        return result;
    }

    public int insert (Vehicle v) throws SQLException {
        String sql = "INSERT INTO vehicles (customer_id, plate, make, model, color) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getCustomerId());
            ps.setString(2, v.getPlate());
            ps.setString(3, v.getMake());
            ps.setString(4, v.getModel());
            ps.setString(5, v.getColour());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next())
                    return keys.getInt(1);
            }
        }
        return -1;
    }

    public void update(Vehicle v) throws SQLException {
        String sql = "UPDATE vehicles SET plate = ?, make = ?, model = ?, colour = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getPlate());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setString(4, v.getColour());
            ps.setInt(5, v.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
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

