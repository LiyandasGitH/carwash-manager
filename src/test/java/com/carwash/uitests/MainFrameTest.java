package com.carwash.uitests;

import com.carwash.ui.MainFrame;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainFrameTest {

    @Test
    void testFrameInitialization() {
        assertDoesNotThrow(() -> {
            MainFrame frame = new MainFrame();

            // Verify base window properties
            assertNotNull(frame, "MainFrame should be successfully instantiated");
            assertEquals("Car Wash Manager", frame.getTitle(), "Frame should have the correct title");
            assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation(), "Frame should exit on close");

            // Verify dimension and layout
            assertEquals(new Dimension(1000, 650), frame.getSize(), "Frame should be 1000x650");
            assertTrue(frame.getContentPane().getLayout() instanceof BorderLayout, "Content pane should use BorderLayout");

            // Verify tab structure
            Component centerComponent = ((BorderLayout) frame.getContentPane().getLayout())
                    .getLayoutComponent(BorderLayout.CENTER);

            assertTrue(centerComponent instanceof JTabbedPane, "Center component should be a JTabbedPane");

            JTabbedPane tabs = (JTabbedPane) centerComponent;
            assertEquals(4, tabs.getTabCount(), "Frame should contain 4 main tabs");
            assertEquals("Customers", tabs.getTitleAt(0));
            assertEquals("Ticket Board", tabs.getTitleAt(1));
            assertEquals("Checkout", tabs.getTitleAt(2));
            assertEquals("Reports", tabs.getTitleAt(3));
        });
    }
}