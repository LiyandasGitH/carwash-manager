package com.carwash.modeltests;

import org.junit.jupiter.api.Test;

import com.carwash.model.Service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    void testGettersAndSetters() {
        Service service = new Service();

        service.setId(23);
        service.setName("surface");
        //  remember to test for BigDecimal price
         service.setPrice(new BigDecimal("50.00"));
        service.setDurationMin(30);

        assertEquals(23, service.getId());
        assertEquals("surface", service.getName());
        assertEquals(new BigDecimal("50.00"), service.getPrice());
        assertEquals(30, service.getDurationMin());
    }
}
