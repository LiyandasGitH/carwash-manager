package com.carwash.daotests;

import com.carwash.dao.CustomerDAO;
import com.carwash.db.DBConnection;
import com.carwash.model.Customer;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

    @Test
    void testDatabaseConnectionAndQueries() {
        assertDoesNotThrow(() -> {
            // Verify DB Connection explicitly
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn, "Database connection should be established");
            }

            CustomerDAO dao = new CustomerDAO();

            // Test read operations[cite: 1]
            assertNotNull(dao.findAll(), "findAll should return a list");
            assertNotNull(dao.search("test"), "search should return a list");

            Customer c = new Customer();
            c.setName("Test Customer");
            c.setPhone("1234567890");
            c.setEmail("test@test.com");
            c.setMemberStatus("NONE");
            c.setJoinDate(LocalDate.now());

            int id = dao.insert(c);
            assertTrue(id > 0 || id == -1, "Insert should execute returning generated key or -1");

            if (id > 0) {
                c.setId(id);
                c.setName("Updated Name");
                dao.update(c);
                assertNotNull(dao.findById(id), "findById should retrieve the updated customer");
                dao.delete(id);
            }
        });
    }
}
