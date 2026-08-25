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

}