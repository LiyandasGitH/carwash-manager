package com.carwash.model;

public class Vehicle {
    private int id;
    private int customerId;
    private String plate;
    private String make;
    private String model;
    private String colour;

    public Vehicle() {}

    public Vehicle(int id, int customerId, String plate, String make, String model, String colour) {
        this.id = id;
        this.customerId = customerId;
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.colour = colour;
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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "number plate of customer " + customerId + " is " + plate;
    }
}
