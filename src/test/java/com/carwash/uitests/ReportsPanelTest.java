package com.carwash.uitests;

import com.carwash.ui.ReportsPanel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReportsPanelTest {

    @Test
    void testPanelInitialization() {
        // calls refresh() which hits the database
        assertDoesNotThrow(() -> {
            ReportsPanel panel = new ReportsPanel();

            // base properties
            assertNotNull(panel, "Panel should be successfully instantiated");
            assertTrue(panel.getLayout() instanceof BorderLayout, "Panel should use BorderLayout");

            // component structure n: summary, c: tables, s: refresh bar
            assertEquals(3, panel.getComponentCount(), "Panel should contain 3 main UI regions");
        });
    }

}
