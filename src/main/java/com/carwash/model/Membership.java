package com.carwash.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Membership {
    private int id;
    private int customerId;
    private String plan; // BASIC, PREMIUM
    private BigDecimal monthlyFee;
    private boolean active;
    private LocalDate renewing;

    public Membership () {}

    public Membership(int id, int customerId, String plan, BigDecimal monthlyFee, boolean active, LocalDate renewing) {
        this.id = id;
        this.customerId = customerId;
        this.plan = plan;
        this.monthlyFee = monthlyFee;
        this.active = active;
        this.renewing = renewing;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRenewing() {
        return renewing;
    }

    public void setRenewing(LocalDate renewing) {
        this.renewing = renewing;
    }

    @Override
    public String toString() {
        return id + customerId + plan + monthlyFee + active + renewing;
    }
}
