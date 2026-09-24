package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM customers ORDER BY name";
        List<Customer> result = new ArrayList<>();
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(map(resultSet));
            }
        }
        return result;
    }

    public List<Customer> search(String keyword) throws SQLException {
        String sql = "SELECT * FROM customers WHERE name LIKE ? OR phone LIKE ? OR email LIKE ? ORDER BY name";
        List<Customer> result = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(map(rs));
                }
            }
            return result;
        }
    }

    public Customer findById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
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

    public int insert(Customer c) throws SQLException {
        String sql = "INSERT INTO customers (name, phone, email, member_status, join_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, c.getName());
            prep.setString(2, c.getPhone());
            prep.setString(3, c.getEmail());
            prep.setString(4, c.getMemberStatus() == null ? "NONE" : c.getMemberStatus());
            prep.setDate(5, Date.valueOf(c.getJoinDate() == null ? LocalDate.now() : c.getJoinDate()));
            prep.executeUpdate();
            try (ResultSet keys = prep.getGeneratedKeys()) {
                if (keys.next())
                    return keys.getInt(1);
            }
        }
        return -1;
    }

    public void update(Customer c) throws SQLException {
        String sql = "UPDATE customers SET name=?, phone=?, email=?, member_status=? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setString(1, c.getName());
            prep.setString(2, c.getPhone());
            prep.setString(3, c.getEmail());
            prep.setString(4, c.getMemberStatus());
            prep.setInt(5, c.getId());
            prep.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setInt(1, id);
            prep.executeUpdate();
        }
    }

    private Customer map(ResultSet resultSet) throws SQLException {
        Customer c = new Customer();
        c.setId(resultSet.getInt("id"));
        c.setName(resultSet.getString("name"));
        c.setPhone(resultSet.getString("phone"));
        c.setEmail(resultSet.getString("email"));
        c.setMemberStatus(resultSet.getString("member_status"));

        Date d = resultSet.getDate("join_date");
        c.setJoinDate(d != null ? d.toLocalDate() : null);

        return c;
    }
}
