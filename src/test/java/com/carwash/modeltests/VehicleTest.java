package com.carwash.modeltests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.carwash.model.Vehicle;

public class VehicleTest {
    
    @Test
    void testGettersAndSetters() {
        Vehicle vehicle = new Vehicle();

        vehicle.setId(2);
        vehicle.setCustomerId(202);
        vehicle.setPlate("CA 987-654");
        vehicle.setMake("Volkswagen");
        vehicle.setModel("Polo");
        vehicle.setColour("Blue");

        assertEquals(2, vehicle.getId());
        assertEquals(202, vehicle.getCustomerId());
        assertEquals("CA 987-654", vehicle.getPlate());
        assertEquals("Volkswagen", vehicle.getMake());
        assertEquals("Polo", vehicle.getModel());
        assertEquals("Blue", vehicle.getColour());
        
    }
}
