package com.carwash.model;

import java.time.LocalDateTime;

public class Ticket {

    private int id;
    private int customerId;
    private int vehicleId;
    private int serviceId;
    private Integer employeeId; // nullable until assigned
    private TicketStatus status;
    private LocalDateTime created;

    public Ticket() {}

    public Ticket(int id, int customerId, int vehicleId, int serviceId, Integer employeeId,
                  TicketStatus status, LocalDateTime created) {
        this.id = id;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.serviceId = serviceId;
        this.employeeId = employeeId;
        this.status = status;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "The ticket id is " + id + ", it belongs to customer " + customerId;
    }
}