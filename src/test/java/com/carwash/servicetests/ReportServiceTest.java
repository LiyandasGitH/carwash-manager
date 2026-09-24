package com.carwash.servicetests;

import com.carwash.service.ReportService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    @Test
    void testRevenueMetricsIntegration() {
        ReportService reportService = new ReportService();

        // Hits the database via paymentDAO.totalRevenueBetween()
        assertDoesNotThrow(() -> {
            BigDecimal revenueToday = reportService.revenueToday();
            assertNotNull(revenueToday, "Revenue today should return a BigDecimal, even if zero");
            assertTrue(revenueToday.compareTo(BigDecimal.ZERO) >= 0, "Revenue should not be negative");

            BigDecimal revenueThisWeek = reportService.revenueThisWeek();
            assertNotNull(revenueThisWeek, "Revenue this week should return a BigDecimal");
            assertTrue(revenueThisWeek.compareTo(BigDecimal.ZERO) >= 0, "Revenue should not be negative");
        });
    }

    @Test
    void testListRetrievalsIntegration() {
        ReportService reportService = new ReportService();

        // Hits the database via paymentDAO.revenueByDay() and paymentDAO.topCustomers()
        assertDoesNotThrow(() -> {
            List<Object[]> revenueByDay = reportService.revenueByDay(14);
            assertNotNull(revenueByDay, "Revenue by day should return a list (can be empty)");

            List<Object[]> topCustomers = reportService.topCustomers(10);
            assertNotNull(topCustomers, "Top customers should return a list (can be empty)");

            // If there are results, verify the structure of the Object[] arrays matches the expected DTO shape
            if (!topCustomers.isEmpty()) {
                Object[] firstCustomer = topCustomers.get(0);
                assertEquals(3, firstCustomer.length, "Top customer record should contain exactly 3 elements [name, totalSpend, visitCount]");
                assertTrue(firstCustomer[0] instanceof String, "First element should be the customer's name");
                assertTrue(firstCustomer[1] instanceof BigDecimal, "Second element should be the total spend");
                assertTrue(firstCustomer[2] instanceof Integer, "Third element should be the visit count");
            }
        });
    }
    
}
