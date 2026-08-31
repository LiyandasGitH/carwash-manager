package com.carwash.modeltests;

import com.carwash.model.Ticket;
import com.carwash.model.TicketStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    void testGettersAndSetters() {
        Ticket ticket = new Ticket();
        LocalDateTime now = LocalDateTime.now();

        ticket.setId(10);
        ticket.setCustomerId(5);
        ticket.setVehicleId(2);
        ticket.setServiceId(3);
        ticket.setEmployeeId(7);
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticket.setCreated(now);

        assertEquals(10, ticket.getId());
        assertEquals(5, ticket.getCustomerId());
        assertEquals(2, ticket.getVehicleId());
        assertEquals(3, ticket.getServiceId());
        assertEquals(7, ticket.getEmployeeId());
        assertEquals(TicketStatus.IN_PROGRESS, ticket.getStatus());
        assertEquals(now, ticket.getCreated());
    }

    @Test
    void testAllArgsConstructorAndNullableEmployee() {
        LocalDateTime now = LocalDateTime.now();
        // Testing constructor with a null employeeId (unassigned)
        Ticket ticket = new Ticket(11, 6, 4, 1, null, TicketStatus.IN_PROGRESS, now);

        assertEquals(11, ticket.getId());
        assertEquals(6, ticket.getCustomerId());
        assertEquals(4, ticket.getVehicleId());
        assertEquals(1, ticket.getServiceId());
        assertNull(ticket.getEmployeeId());
        assertEquals(TicketStatus.IN_PROGRESS, ticket.getStatus());
        assertEquals(now, ticket.getCreated());
    }
    
}
