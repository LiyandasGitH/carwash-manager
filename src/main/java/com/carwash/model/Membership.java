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

    }
}
