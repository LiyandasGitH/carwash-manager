package com.carwash.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private int id;
    private int ticketId;
    private BigDecimal amount;
    private Method method;
    private PaymentStatus status;
    /*
    * need to revisit the naming of the below
    * paid does not seem like the correct way to name something with LocalDateTime as an Object
    * */
    private LocalDateTime paidAt;

    public Payment() {}

    public Payment(int id, int ticketId, BigDecimal amount, Method method, PaymentStatus status, LocalDateTime paidAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paidAt = paidAt;
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

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    @Override
    public String toString() {
        return "Payment #" + id + " R" + amount + " with " + method + " [" + status + "]";
    }
}
