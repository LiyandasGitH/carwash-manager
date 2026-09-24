package com.carwash.daotests;

import com.carwash.dao.EmployeeDAO;
import com.carwash.db.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDAOTest {

    @Test
    void testEmployeeQueries() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            EmployeeDAO dao = new EmployeeDAO();

            assertNotNull(dao.findAll(), "findAll should return a list");

            assertNull(dao.findById(999), "findById should return null for non-existent ID");
        });
    }
}
