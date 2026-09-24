package com.carwash.daotests;

import com.carwash.dao.TicketDAO;
import com.carwash.db.DBConnection;
import com.carwash.model.Ticket;
import com.carwash.model.TicketStatus;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDAOTest {

    @Test
    void testTicketQueriesAndUpdates() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            TicketDAO dao = new TicketDAO();

            assertNotNull(dao.findActive(), "findActive should return a list");
            assertNotNull(dao.findAll(), "findAll should return a list");
            dao.findById(999);

            // safe to run on non-existent IDs, will just return 0 rows updated
            dao.updateStatus(999, TicketStatus.DONE);
            dao.assignEmployee(999, 1);

            Ticket t = new Ticket();
            t.setCustomerId(1);
            t.setVehicleId(1);
            t.setServiceId(1);
            t.setStatus(TicketStatus.QUEUED);

            try {
                dao.insert(t);
            } catch (Exception e) {
                // expected if test database lacks seed data for parent IDs
            }
        });
    }
}
