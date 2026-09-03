package com.carwash.dao;

import com.carwash.db.DBConnection;
import com.carwash.model.Customer;

import java.sql.*;
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
