package com.carwash.model;

import java.math.BigDecimal;

public class Service {
    private int id;
    private String name;
    private BigDecimal price;
    private int durationMin;

    public Service() {}

    public Service(int id, String name, BigDecimal price, in durationMin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationMin = durationMin;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    private int getDurationMin() {
        return durationMin;
    }

    private void setDurationMin(int duration) {
        this.durationMin = duration;
    }

    @Override
    public String toString() {
        return name + " price is " + price;
    }
}
