package com.carwash.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final TicketBoardPanel ticketBoardPanel;
    private final CheckoutPanel checkoutPanel;

    public MainFrame() {
        super("Car Wash Manager");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        CustomerPanel customerPanel = new CustomerPanel();
        ticketBoardPanel = new TicketBoardPanel();
        checkoutPanel = new CheckoutPanel();
        ReportsPanel reportsPanel = new ReportsPanel();

        // checkout tab should refresh ticket board after a payment
        // let ticket jump straight to check out for DONE ticket
        checkoutPanel.setOnPaymentComplete(ticketBoardPanel::refresh);
        ticketBoardPanel.setOnCheckoutRequested(ticketId -> {
            tabs.setSelectedIndex(2);
            checkoutPanel.loadTicket(ticketId);
        });

        tabs.addTab("Customers", customerPanel);
        tabs.addTab("Ticket Board", ticketBoardPanel);
        tabs.addTab("Checkout", checkoutPanel);
        tabs.addTab("Reports", reportsPanel);

        tabs.addChangeListener(e -> {
            int idx = tabs.getSelectedIndex();
            if (idx == 1) ticketBoardPanel.refresh();
            if (idx == 3) reportsPanel.refresh();
        });

        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }
}

