package com.carwash.modeltests;

import com.carwash.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class CustomerTest {
    
    @Test 
    void testGettersandSetters() {
        Customer customer = new Customer();

        customer.setId(1);
        customer.setName("Ned Stark");
        customer.setPhone("070 000 0000");
        customer.setEmail("nedstark@example.com");
        customer.setMemberStatus("PREMIUM");
        LocalDate joined = LocalDate.of(2026, 8, 22);
        customer.setJoinDate(joined);

        assertEquals(1, customer.getId());
        assertEquals("Ned Stark", customer.getName());
        assertEquals("070 000 0000", customer.getPhone());
        assertEquals("nedstark@example.com", customer.getEmail());
        assertEquals("PREMIUM", customer.getMemberStatus());
        assertEquals(joined, customer.getJoinDate());
    }
}
