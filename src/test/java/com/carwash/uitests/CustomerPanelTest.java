package com.carwash.uitests;

import com.carwash.ui.CustomerPanel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerPanelTest {

    @Test
    void testPanelInitialization() {
        // calls loadCustomers() which hits the database
        assertDoesNotThrow(() -> {
            CustomerPanel panel = new CustomerPanel();

            assertNotNull(panel, "Panel should be initialized");
            assertTrue(panel.getLayout() instanceof BorderLayout, "Panel should use BorderLayout");

            assertEquals(3, panel.getComponentCount(), "Panel should contain 3 main regions");
        });
    }
}
