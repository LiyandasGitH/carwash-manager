package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Service;
import com.sun.source.tree.BreakTree;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    public List<Service> findAll() throws SQLException {
        String sql = "SELECT * FROM services ORDER BY price";
        List<Service> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(map(rs));
        }
        return result;
    }

    public Service findById(int id) throws SQLException {
        String sql = "SELECT * FROM services ORDER BY price";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    private Service map(ResultSet rs) throws SQLException {
        Service s = new Service();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setPrice(rs.getBigDecimal("price"));
        s.setDurationMin(rs.getInt("duration_min"));
        return s;
    }
}
