package com.carwash.dummy;
import java.awt.*;
import java.awt.event.*;

public class AwtExample {
    public static void main(String[] args) {
        // Create the top-level window container
        Frame frame = new Frame("AWT Window ");

        // Create a clickable UI component
        Button button = new Button("Click Me");

        // Register an event listener to handle button clicks
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button was clicked!");
            }
        });

        // Add the button to the frame container
        frame.add(button);

        // Define layout structure and window dimensions
        frame.setLayout(new FlowLayout());
        frame.setSize(200, 200);

        // Make the window visible to the user
        frame.setVisible(true);

        // Handle window close event natively
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
