package com.carwash.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private int id;
    private int ticketId;
    private BigDecimal amount;
    private Method method;
    private PaymentStatus status;
    private LocalDateTime paid;

    public Payment() {}

    public Payment(int id, int ticketId, BigDecimal amount, Method method, PaymentStatus status, LocalDateTime paid) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaid() {
        return paid;
    }

    public void setPaid(LocalDateTime paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Payment #" + id + " R" + amount + " with " + method + " [" + status + "]";
    }
}
