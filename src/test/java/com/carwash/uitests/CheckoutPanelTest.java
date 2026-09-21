package com.carwash.uitests;

import com.carwash.ui.CheckoutPanel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutPanelTest {

    @Test
    void testPanelInitialization() {
        assertDoesNotThrow(() -> {
            CheckoutPanel panel = new CheckoutPanel();

            assertNotNull(panel, "Panel should be successfully instantiated");
            assertTrue(panel.getLayout() instanceof BorderLayout, "Panel should use BorderLayout");

            assertEquals(3, panel.getComponentCount(), "Panel should contain 3 main UI regions");
        });
    }

    @Test
    void testSetOnPaymentComplete() {
        CheckoutPanel panel = new CheckoutPanel();
        AtomicBoolean callbackTriggered = new AtomicBoolean(false);

        assertDoesNotThrow(() -> {
            panel.setOnPaymentComplete(() -> callbackTriggered.set(true));
        }, "Setting the payment completion callback should not throw any exceptions");
    }
}
