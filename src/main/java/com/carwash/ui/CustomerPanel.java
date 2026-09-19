package com.carwash.ui;

import com.carwash.dao.CustomerDAO;
import com.carwash.dao.VehicleDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

    public  CustomerPanel() {
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

    }

}

