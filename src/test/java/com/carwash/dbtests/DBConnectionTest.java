package com.carwash.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectionTest {
    @Test
    void testGetConnection() {
        // This test will attempt a live connection using your environment variables or defaults
        try (Connection connection = DBConnection.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("Database connection failed: " + e.getMessage());
        }
    }
}
