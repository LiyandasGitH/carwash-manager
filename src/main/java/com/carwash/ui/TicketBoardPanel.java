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

    }
}
