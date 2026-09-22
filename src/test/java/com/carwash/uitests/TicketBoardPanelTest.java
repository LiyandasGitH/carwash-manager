package com.carwash.uitests;

import com.carwash.ui.TicketBoardPanel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TicketBoardPanelTest {

    @Test
    void testPanelInitialization() {
        // calls refresh() and buildCheckInForm(), which hit the database
        assertDoesNotThrow(() -> {
            TicketBoardPanel panel = new TicketBoardPanel();

            // base properties
            assertNotNull(panel, "Panel should be successfully instantiated");
            assertTrue(panel.getLayout() instanceof BorderLayout, "Panel should use BorderLayout");

            // component structure; check-in form, c: table, south: action bar
            assertEquals(3, panel.getComponentCount(), "Panel should contain 3 main UI regions");
        });
    }

    @Test
    void testSetOnCheckoutRequested() {
        TicketBoardPanel panel = new TicketBoardPanel();
        AtomicInteger capturedId = new AtomicInteger(-1);

        // ensure that the IntConsumer callback setter binds correctly
        assertDoesNotThrow(() -> {
            panel.setOnCheckoutRequested(id -> capturedId.set(id));
        }, "Setting the checkout requested callback should not throw any exceptions");
    }
}
