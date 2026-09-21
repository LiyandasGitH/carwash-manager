package com.carwash.ui;

import com.carwash.dao.ServiceDAO;
import com.carwash.model.Method;
import com.carwash.model.Ticket;
import com.carwash.service.PaymentService;
import com.carwash.service.TicketService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class CheckoutPanel extends JPanel {

    private final TicketService ticketService = new TicketService();
    private final PaymentService paymentService = new PaymentService();
    private final ServiceDAO serviceDAO = new ServiceDAO();

    private final JTextField ticketIdField = new JTextField(6);
    private final JButton loadBtn = new JButton("Load Ticket");

    private final JLabel customerLabel = new JLabel("-");
    private final JLabel vehicleLabel = new JLabel("-");
    private final JLabel serviceLabel = new JLabel("-");
    private final JLabel statusLabel = new JLabel("-");
    private final JLabel priceLabel = new JLabel("R0.00");

    private final JComboBox<Method> methodCombo = new JComboBox<>(Method.values());
    private final JButton payBtn = new JButton("Charge Payment");
    private final JTextArea receiptArea = new JTextArea(10, 40);

    private Ticket currentTicket;
    private BigDecimal currentPrice;

    private Runnable onPaymentComplete = () -> {};

    public void setOnPaymentComplete(Runnable callback) {
        this.onPaymentComplete = callback;
    }

    public CheckoutPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        add(buildLoadBar(), BorderLayout.NORTH);
        add(buildSummaryPanel(), BorderLayout.CENTER);

        receiptArea.setEditable(false);
        receiptArea.setBorder(BorderFactory.createTitledBorder("Receipt"));

        add(new JScrollPane(receiptArea), BorderLayout.SOUTH);
        loadBtn.addActionListener(e -> loadFromField());
        payBtn.addActionListener(e-> chargePayment());
        payBtn.setEnabled(false);
    }

    private JPanel buildLoadBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(new JLabel("Ticket ID:"));
        panel.add(ticketIdField);
        panel.add(loadBtn);

        return panel;
    }

    private JPanel buildSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Ticket Summary"));

        panel.add(new JLabel("Customer"));
        panel.add(customerLabel);
        panel.add(new JLabel("Vehicle:"));
        panel.add(vehicleLabel);
        panel.add(new JLabel("Service:"));
        panel.add(serviceLabel);
        panel.add(new JLabel("Status:"));
        panel.add(statusLabel);
        panel.add(new JLabel("Amount Due:"));
        panel.add(priceLabel);
        panel.add(new JLabel("Payment Method:"));
        panel.add(methodCombo);
        panel.add(new JLabel(""));
        panel.add(payBtn);

        return panel;

    }

    private void loadFromField() {
        String text = ticketIdField.getText().trim();
        if (text.isEmpty()) return;
        try {
            loadTicket(Integer.parseInt(text));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ticket ID must be a number.", "Invalid input",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * load ticket w/ price & computes its price (w/ any membership discount applied)
     * */
    public void loadTicket(int ticketId) {
        try {
            Ticket t = ticketService.getTicket(ticketId);
            if (t == null) {
                JOptionPane.showMessageDialog(this, "No ticket with ID" + ticketId, "Not found",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            currentTicket = t;
        } catch (SQLException ex) {
            showDbError(ex);
        }

    }

    private void chargePayment() {

    }

    private String buildReceiptText(PaymentService.Receipt receipt) {
        return "";
    }

    private void showDbError(SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
