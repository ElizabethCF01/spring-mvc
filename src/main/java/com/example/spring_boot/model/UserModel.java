package com.example.spring_boot.model;

import java.util.Date;

public class UserModel {

    private int id;
    private String email;
    private String ipAddress;
    private Date createdAt;

    public UserModel(int id,  String email, String ipAddress) {
        this.id = id;
        this.email = email;
        this.ipAddress = ipAddress;
        this.createdAt = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "EmailSubscription{" +
                "email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", subscriptionDate=" + createdAt +
                '}';
    }
}
