package com.carwash.ui;

import com.carwash.dao.CustomerDAO;
import com.carwash.dao.VehicleDAO;
import com.carwash.model.Customer;
import com.carwash.model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;

public class CustomerPanel extends JPanel {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final VehicleDAO vehicleDAO = new VehicleDAO();

    private final DefaultTableModel customerModel = new DefaultTableModel(
            new Object[] {"ID", "Name", "Phone", "Email", "Status"}, 0) {
                @Override public boolean isCellEditable(int row, int col) {
                    return false;
                }
    };

    private final JTable customerTable = new JTable(customerModel);

    private final DefaultTableModel vehicleModel = new DefaultTableModel(
            new Object[]{"ID", "Plate", "Make", "Model", "Colour"}, 0) {
        @Override public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    private final JTable vehicleTable = new JTable(vehicleModel);

    private final JTextField searchField = new JTextField(15);

    public CustomerPanel() {
        setLayout(new BorderLayout(8, 8));

        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // search bar
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Search:"));
        top.add(searchField);

        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");
        top.add(searchBtn);
        top.add(clearBtn);
        add(top, BorderLayout.NORTH);

        // customer table + vehicle table
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split.setTopComponent(customerTable);

        JPanel vehiclePanel = new JPanel(new BorderLayout());
        vehiclePanel.add(new JLabel("Vehicles for selected customer:"), BorderLayout.NORTH);
        vehiclePanel.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);

        split.setBottomComponent(vehiclePanel);
        split.setResizeWeight(0.6);
        add(split, BorderLayout.CENTER);

        // action buttons
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addCustomerBtn = new JButton("Add Customer");
        JButton editCustomerBtn = new JButton("Edit Customer");
        JButton addVehicleBtn = new JButton("Add Vehicle");

        actions.add(addCustomerBtn);
        actions.add(editCustomerBtn);
        actions.add(addVehicleBtn);
        add(actions, BorderLayout.SOUTH);

        // listners
        searchBtn.addActionListener(e ->
                loadCustomers(searchField.getText().trim()));
        clearBtn.addActionListener(e -> {
            searchField.setText(""); loadCustomers("");
        });

        customerTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadVehiclesForSelected();
        });

        addCustomerBtn.addActionListener(e -> showAddCustomerDialog());
        editCustomerBtn.addActionListener(e -> showEditCustomerDialog());
        addVehicleBtn.addActionListener(e -> showAddVehicleDialog());

        loadCustomers("");
    }

    private void loadCustomers(String keyword) {
        try {
            List<Customer> customers = keyword.isEmpty()
                    ? customerDAO.findAll()
                    : customerDAO.search(keyword);
            customerModel.setRowCount(0);
            for (Customer c : customers) {
                customerModel.addRow(new Object[] {
                        c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getMemberStatus()
                });
            }
            vehicleModel.setRowCount(0);
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private Integer getSelectedCustomerId() {
        int row = customerTable.getSelectedRow();
        if (row < 0) return null;
        return (Integer) customerModel.getValueAt(row, 0);
    }

    private void loadVehiclesForSelected() {
        Integer customerId = getSelectedCustomerId();
        vehicleModel.setRowCount(0);
        if (customerId == null) return;
        try {
            List<Vehicle> vehicles = vehicleDAO.findByCustomer(customerId);
            for (Vehicle v : vehicles) {
                vehicleModel.addRow(new Object[] {
                        v.getId(), v.getPlate(), v.getMake(), v.getModel(), v.getColour()
                });
            }
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void showAddCustomerDialog() {
        JTextField name = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        String[] types = {"NONE", "BASIC", "PREMIUM"};
        JComboBox<String> status = new JComboBox<>(types);

        JPanel form = formPanel(
                "Name:", name, "Phone:", phone, "Email:", email, "Member Status:", status
        );

        int result = JOptionPane.showConfirmDialog(this, form, "Add Customer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_CANCEL_OPTION) return;

        if (name.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.", "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Customer c = new Customer();
        c.setName(name.getText().trim());
        c.setPhone(phone.getText().trim());
        c.setEmail(email.getText().trim());
        c.setMemberStatus((String) status.getSelectedItem());
        c.setJoinDate(LocalDate.now());

        try {
            customerDAO.insert(c);
            loadCustomers(searchField.getText().trim());
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void showEditCustomerDialog() {
        Integer id = getSelectedCustomerId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Select a customer first.", "No selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Customer c = customerDAO.findById(id);
            if (c == null) return;

            JTextField name = new JTextField(c.getName());
            JTextField phone = new JTextField(c.getPhone());
            JTextField email = new JTextField(c.getEmail());
            String[] types = {"NONE", "BASIC", "PREMIUM"};
            JComboBox<String> status = new JComboBox<>(types);
            status.setSelectedItem(c.getMemberStatus());

            JPanel form = formPanel(
                    "Name:", name, "Phone:", phone, "Email:", email, "Member Status:", status
            );
            int result = JOptionPane.showConfirmDialog(this, form, "Edit Customer",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            c.setName(name.getText().trim());
            c.setPhone(phone.getText().trim());
            c.setEmail(email.getText().trim());
            c.setMemberStatus((String) status.getSelectedItem());
            customerDAO.update(c);

            loadCustomers(searchField.getText().trim());
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void showAddVehicleDialog() {
        Integer customerId = getSelectedCustomerId();
        if (customerId == null) {
            JOptionPane.showMessageDialog(this, "Select a customer first.", "No selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JTextField plate = new JTextField();
        JTextField make = new JTextField();
        JTextField model = new JTextField();
        JTextField colour = new JTextField();

        JPanel form = formPanel(
                "Plate:", plate, "Make:", make, "Model:", model, "Colour:", colour
        );

        int result = JOptionPane.showConfirmDialog(this, form, "Add Vehicle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return;
        if (plate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plate is required.", "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle v = new Vehicle();
        v.setCustomerId(customerId);
        v.setPlate(plate.getText().trim());
        v.setMake(make.getText().trim());
        v.setModel(model.getText().trim());
        v.setColour(colour.getText().trim());

        try {
            vehicleDAO.insert(v);
            loadVehiclesForSelected();
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private JPanel formPanel(Object... labelsAndFields) {

        if (labelsAndFields.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments must be provided in matching pairs");
        }

        JPanel panel = new JPanel(new GridLayout(labelsAndFields.length / 2, 2, 6, 6));
        for (Object o : labelsAndFields) {
            if (o instanceof String s) {
                panel.add(new JLabel(s));
            } else if (o instanceof Component c) {
                panel.add(c);
            }
            else {
                String type = (o != null) ? o.getClass().getSimpleName() : "null";
                throw new IllegalArgumentException("Invalid argument: Expected String or Component, but got " + type);
            }
        }
        return panel;
    }

    private void showDbError(SQLException ex) {
        JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

