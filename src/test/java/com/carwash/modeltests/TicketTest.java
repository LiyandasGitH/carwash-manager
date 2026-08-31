package com.carwash.modeltests;

import com.carwash.model.Ticket;
import com.carwash.model.TicketStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setCreated(now);

        assertEquals(10, ticket.getId());
        assertEquals(5, ticket.getCustomerId());
        assertEquals(2, ticket.getVehicleId());
        assertEquals(3, ticket.getServiceId());
        assertEquals(7, ticket.getEmployeeId());
        assertEquals(TicketStatus.PENDING, ticket.getStatus());
        assertEquals(now, ticket.getCreated());
    }
    
}
