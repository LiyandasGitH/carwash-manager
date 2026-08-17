package com.carwash.model;

import java.time.LocalDate;

public class Customer {
    private final int id;
    private final String name;
    private String phone;
    private String email;
    private String memberStatus; // NONE, BASIC, PREMIUM
    private LocalDate joinDate;

    public Customer(int id, String name, String phone, String email, String memberStatus, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.memberStatus = memberStatus;
        this.joinDate = joinDate;
    }

    

}
