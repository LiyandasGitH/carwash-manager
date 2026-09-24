package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT * FROM employees ORDER BY name";
        List<Employee> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(map(rs));
        }
        return result;
    }

    public Employee findById(int id) throws SQLException {
        String sql = "SELECT * employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next())
                    return map(rs);
            }
        }
        return null;
    }

    private Employee map(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setRole(rs.getString("role"));

        return e;
    }
}
