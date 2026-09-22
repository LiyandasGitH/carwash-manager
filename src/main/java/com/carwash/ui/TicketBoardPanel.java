package com.carwash.ui;

import com.carwash.dao.CustomerDAO;
import com.carwash.dao.EmployeeDAO;
import com.carwash.dao.ServiceDAO;
import com.carwash.dao.VehicleDAO;
import com.carwash.model.Customer;
import com.carwash.model.Service;
import com.carwash.model.Vehicle;
import com.carwash.service.TicketService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.IntConsumer;

public class TicketBoardPanel extends JPanel {

    private final TicketService ticketService = new TicketService();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    private final DefaultTableModel boardModel = new DefaultTableModel(
            new Object[]{
                    "Ticket", "Customer", "Vehicle", "Service", "Employee", "Status"}, 0) {
            @Override public boolean isCellEditable(int row, int col) {
                return false;
            }
    };
    private final JTable boardTable = new JTable(boardModel);

    private JComboBox<Customer> customerCombo;
    private JComboBox<Vehicle> vehicleCombo;
    private JComboBox<Service> serviceCombo;

    // when user wants to send 'DONE' ticket to checkout
    private IntConsumer onCheckoutRequested = id -> {};

    public void setOnCheckoutRequested(IntConsumer callback) {
        this.onCheckoutRequested = callback;
    }

    public TicketBoardPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    }

    private JPanel buildCheckInForm() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        return panel;
    }

    private void refreshVehicleCombo() {

    }

    private void doCheckIn() {

    }

    private JPanel buildActionBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton assignBtn = new JButton("Assign Employee/Start");
        JButton doneBtn = new JButton("Mark Done");
        JButton checkoutBtn = new JButton("Send to Checkout");
        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton refreshBtn = new JButton("Refresh");

        assignBtn.addActionListener(e -> assignEmployee());
        doneBtn.addActionListener(e -> markDone());
        checkoutBtn.addActionListener(e -> sendToCheckout());
        cancelBtn.addActionListener(e -> cancelTicket());
        refreshBtn.addActionListener(e -> refresh());

        panel.add(assignBtn);
        panel.add(doneBtn);
        panel.add(checkoutBtn);
        panel.add(cancelBtn);
        panel.add(refreshBtn);

        return panel;
    }

    private Integer getSelectedTicketId() {
        return 0;
    }

    private void assignEmployee() {

    }

    private void markDone() {

    }

    private void sendToCheckout() {

    }

    private void cancelTicket() {

    }

    private void warnNoSelection() {

    }

    private void refresh() {

    }

    private void showDbError(SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}
