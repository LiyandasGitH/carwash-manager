package com.carwash.service;

import com.carwash.dao.TicketDAO;
import com.carwash.model.Ticket;
import com.carwash.model.TicketStatus;

import java.sql.SQLException;
import java.util.List;

public class TicketService {

    private final TicketDAO ticketDAO = new TicketDAO();

    public int checkIn(int customerId, int vehicleId, int serviceId) throws SQLException {
        Ticket t = new Ticket();
        t.setCustomerId(customerId);
        t.setVehicleId(vehicleId);
        t.setServiceId(serviceId);
        t.setStatus(TicketStatus.QUEUED);
        return ticketDAO.insert(t);
    }

    public List<Ticket> getActiveBoard() throws SQLException {
        return ticketDAO.findActive();
    }

    public List<Ticket> getAllTickets() throws SQLException {
        return ticketDAO.findAll();
    }

    public void assignEmployee(int ticketId, int employeeId) throws SQLException {
        ticketDAO.assignEmployee(ticketId, employeeId);
        ticketDAO.updateStatus(ticketId, TicketStatus.IN_PROGRESS);
    }

    public void markDone(int ticketId) throws SQLException {
        ticketDAO.updateStatus(ticketId, TicketStatus.DONE);
    }

    public void cancel(int ticketId) throws SQLException {
        ticketDAO.updateStatus(ticketId, TicketStatus.CANCELLED);
    }

    public Ticket getTicket(int ticketId) throws SQLException {
        return ticketDAO.findById(ticketId);
    }
    
}
