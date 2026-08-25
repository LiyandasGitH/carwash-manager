package com.carwash.modeltests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.carwash.model.Employee;

public class EmployeeTest {

    @Test
    void testGettersAndSetters() {
        Employee employee = new Employee();

        employee.setId(10);
        employee.setName("Vayon Poole");
        employee.setRole("Manager");

        assertEquals(10, employee.getId());
        assertEquals("Vayon Poole", employee.getName());
        assertEquals("Manager", employee.getRole());
    }
    

}
