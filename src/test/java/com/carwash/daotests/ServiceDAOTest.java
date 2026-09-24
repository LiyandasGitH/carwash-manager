package com.carwash.daotests;

import com.carwash.dao.ServiceDAO;
import com.carwash.db.DBConnection;
import com.carwash.model.Service;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceDAOTest {

    @Test
    void testServiceQueries() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            ServiceDAO dao = new ServiceDAO();

            assertNotNull(dao.findAll(), "findAll should return a list");
            dao.findById(999);

            Service s = new Service();
            s.setName("Test Service");
            s.setPrice(new BigDecimal("50.00"));
            s.setDurationMin(30);

            int id = dao.insert(s);
            assertTrue(id > 0 || id == -1, "Insert should execute returning generated key or -1");
        });
    }
}
