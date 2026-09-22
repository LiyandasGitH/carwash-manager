package com.carwash.servicetests;

import com.carwash.model.Ticket;
import com.carwash.service.TicketService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {

    @Test
    void testCheckInIntegration() {
        TicketService ticketService = new TicketService();

        // hits the database via ticketDAO.insert()
        assertDoesNotThrow(() -> {
            // assumes customerId 1, vehicleId 1, and serviceId 1 exist in the database to prevent foreign key errors
            int ticketId = ticketService.checkIn(1, 1, 1);
            assertTrue(ticketId >= 0, "Check-in should execute successfully and return a generated ticket ID");
        });
    }

    @Test
    void testRetrievalMethodsIntegration() {
        TicketService ticketService = new TicketService();

        // hits the database via findActive(), findAll(), and findById()
        assertDoesNotThrow(() -> {
            List<Ticket> activeTickets = ticketService.getActiveBoard();
            assertNotNull(activeTickets, "Active board retrieval should return a list (can be empty)");

            List<Ticket> allTickets = ticketService.getAllTickets();
            assertNotNull(allTickets, "All tickets retrieval should return a list");

            Ticket ticket = ticketService.getTicket(999);
        });
    }

    @Test
    void testStatusUpdatesIntegration() {
        TicketService ticketService = new TicketService();
        int dummyTicketId = 999;
        int dummyEmployeeId = 1;

        // hits the database via ticketDAO.updateStatus() and assignEmployee()
        assertDoesNotThrow(() -> {
            ticketService.assignEmployee(dummyTicketId, dummyEmployeeId);
            ticketService.markDone(dummyTicketId);
            ticketService.cancel(dummyTicketId);
        });
    }
    
}
