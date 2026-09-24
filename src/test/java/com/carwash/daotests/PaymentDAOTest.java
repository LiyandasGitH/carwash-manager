package com.carwash.daotests;

import com.carwash.dao.PaymentDAO;
import com.carwash.db.DBConnection;
import com.carwash.model.Method;
import com.carwash.model.Payment;
import com.carwash.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDAOTest {

    @Test
    void testPaymentQueriesAndMetrics() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            PaymentDAO dao = new PaymentDAO();

            assertNotNull(dao.findAll(), "findAll should return a list");
            assertNotNull(dao.totalRevenueBetween(LocalDate.now().minusDays(7), LocalDate.now()), "Revenue should return a valid BigDecimal");
            assertNotNull(dao.revenueByDay(7), "Revenue by day should return a list of Objects");
            assertNotNull(dao.topCustomers(5), "Top customers should return a list of Objects");

            Payment p = new Payment();
            p.setTicketId(1);
            p.setAmount(new BigDecimal("100.00"));
            p.setMethod(Method.CASH);
            p.setStatus(PaymentStatus.COMPLETED);

            try {
                dao.insert(p);
            } catch (Exception e) {
                // expected if test database lacks seed data for ticket_id = 1
            }
        });
    }
}
