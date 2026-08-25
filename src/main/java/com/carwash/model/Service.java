package com.carwash.model;

import java.math.BigDecimal;

public class Service {
    private int id;
    private String name;
    private BigDecimal price;
    private int durationMin;

    public Service() {}

    public Service(int id, String name, BigDecimal price, int durationMin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationMin = durationMin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int duration) {
        this.durationMin = duration;
    }

    @Override
    public String toString() {
        return name + " price is " + price;
    }
}
