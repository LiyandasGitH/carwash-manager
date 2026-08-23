package com.carwash.modeltests;

import org.junit.jupiter.api.Test;
import org.junit.jpiter.api.Assertions.*;

import com.carwash.model.Service;

public class ServiceTest {

    @Test
    void testGettersandSetters() {
        Service service = new Service();

        service.setId(23);
        service.setName("surface");
        //  remember to test for BigDecimal price
        // service.setPrice();
        service.setDurationMin(30);

        assertEquals(23, service.getId());
        assertEquals("surface", service.getName());
        // assertEquals(50.00, service.getPrice());
        assertEquals(30, service.getDurationMin());
    }
}
