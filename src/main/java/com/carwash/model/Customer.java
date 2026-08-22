package com.carwash.model;

import java.time.LocalDate;

public class Customer {
    private final int id;
    private final String name;
    private String phone;
    private String email;
    private String memberStatus; // NONE, BASIC, PREMIUM
    private LocalDate joinDate;

    public Customer() {}

    public Customer(int id, String name, String phone, String email, String memberStatus, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.memberStatus = memberStatus;
        this.joinDate = joinDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentExeption();
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String status) {
        this.memberStatus = status;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joined) {
        this.joinDate = joined;
    }

    @Override
    public String toString() {
        return name + " is a " + (memberStatus != null) ? + memberStatus + " customer at Sifiso's Car Wash.";
    }


}
