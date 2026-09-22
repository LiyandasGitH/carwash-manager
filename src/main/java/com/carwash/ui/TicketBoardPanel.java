package com.carwash.ui;

import com.carwash.dao.CustomerDAO;
import com.carwash.dao.EmployeeDAO;
import com.carwash.dao.ServiceDAO;
import com.carwash.dao.VehicleDAO;
import com.carwash.model.*;
import com.carwash.service.TicketService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.IntConsumer;

import java.util.List;

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

        add(buildCheckInForm(), BorderLayout.NORTH);
        add(new JScrollPane(boardTable), BorderLayout.CENTER);
        add(buildActionBar(), BorderLayout.SOUTH);

        refresh();
    }

    private JPanel buildCheckInForm() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.setBorder(BorderFactory.createTitledBorder("Check In"));

        customerCombo = new JComboBox<>();
        vehicleCombo = new JComboBox<>();
        serviceCombo = new JComboBox<>();

        try {
            for (Customer c : customerDAO.findAll())
                customerCombo.addItem(c);

            for (Service s : serviceDAO.findAll())
                serviceCombo.addItem(s);
        } catch (SQLException ex) {
            showDbError(ex);
        }

        customerCombo.addActionListener(e -> refreshVehicleCombo());
        refreshVehicleCombo();

        JButton checkInBtn = new JButton("Check In");
        checkInBtn.addActionListener(e -> doCheckIn());

        panel.add(new JLabel("Customer:"));
        panel.add(customerCombo);
        panel.add(new JLabel("Vehicle:"));
        panel.add(vehicleCombo);
        panel.add(new JLabel("Service:"));
        panel.add(serviceCombo);
        panel.add(checkInBtn);

        return panel;
    }

    private void refreshVehicleCombo() {

        vehicleCombo.removeAllItems();
        Customer selected = (Customer) customerCombo.getSelectedItem();
        if (selected == null) return;

        try {
            for (Vehicle v : vehicleDAO.findByCustomer(selected.getId()))
                vehicleCombo.addItem(v);
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void doCheckIn() {
        Customer customer = (Customer) customerCombo.getSelectedItem();
        Vehicle vehicle = (Vehicle) vehicleCombo.getSelectedItem();
        Service service = (Service) serviceCombo.getSelectedItem();
        if (customer == null || vehicle == null || service == null) {
            JOptionPane.showMessageDialog(this, "Select a customer, vehicle, and service.", "Missing info",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            ticketService.checkIn(customer.getId(), vehicle.getId(), service.getId());
            refresh();
        } catch (SQLException ex) {
            showDbError(ex);
        }
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
        int row = boardTable.getSelectedRow();
        if (row < 0) return null;
        return (Integer) boardModel.getValueAt(row, 0);
    }

    private void assignEmployee() {
        Integer ticketId = getSelectedTicketId();
        if (ticketId == null) {
            warnNoSelection();
            return;
        }
        try {
            List<Employee> employees = employeeDAO.findAll();
            Employee chosen = (Employee) JOptionPane.showInputDialog(this, "Assign employee:", "Assign Employee",
                    JOptionPane.PLAIN_MESSAGE, null, employees.toArray(),
                    employees.isEmpty() ? null : employees.get(0));
            if (chosen == null) return;
            ticketService.assignEmployee(ticketId, chosen.getId());
            refresh();
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void markDone() {
        Integer ticketId = getSelectedTicketId();
        if (ticketId == null) {
            warnNoSelection();
            return;
        }
        try {
            ticketService.markDone(ticketId);
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void sendToCheckout() {
        Integer ticketId = getSelectedTicketId();
        if (ticketId == null) {
            warnNoSelection();
            return;
        }
        onCheckoutRequested.accept(ticketId);
    }

    private void cancelTicket() {
        Integer ticketId = getSelectedTicketId();
        if (ticketId == null) {
            warnNoSelection();
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Cancel this ticket?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            ticketService.cancel(ticketId);
            refresh();
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void warnNoSelection() {
        JOptionPane.showMessageDialog(this, "Select a ticket first.", "No selection",
                JOptionPane.WARNING_MESSAGE);
    }

    public void refresh() {
        try {
            boardModel.setRowCount(0);
            for (Ticket t : ticketService.getActiveBoard()) {
                boardModel.addRow(new Object[]{
                        t.getId(), t.getCustomerName(), t.getVehicleLabel(), t.getServiceName(),
                        t.getEmployeeName() == null ? "-" : t.getEmployeeName(), t.getStatus()
                });
            }
            // refrresh customer/vehicle combos in case new ones were added
            Object selectedCustomer = customerCombo.getSelectedItem();
            customerCombo.removeAllItems();
            for (Customer c : customerDAO.findAll())
                customerCombo.addItem(c);
            if (selectedCustomer != null) {
                customerCombo.setSelectedItem((selectedCustomer));
            }
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void showDbError(SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }

}
