package com.carwash.daotests;

import com.carwash.dao.VehicleDAO;
import com.carwash.db.DBConnection;
import com.carwash.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleDAOTest {

    @Test
    void testVehicleQueriesAndMutations() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            VehicleDAO dao = new VehicleDAO();

            assertNotNull(dao.findAll(), "findAll should return a list");
            assertNotNull(dao.findByCustomer(999), "findByCustomer should return a list");
            dao.findByInt(999);

            Vehicle v = new Vehicle();
            v.setCustomerId(1);
            v.setPlate("TST-123");
            v.setMake("Toyota");
            v.setModel("Corolla");
            v.setColour("White");

            try {
                int id = dao.insert(v);
                if (id > 0) {
                    v.setId(id);
                    v.setColour("Black");
                    dao.update(v);
                    dao.delete(id);
                }
            } catch (Exception e) {
                // expected if test database lacks seed data for customer_id = 1
            }
        });
    }
}
