package com.example.spring_boot.dto;

public class UserRequest {
    public String email;
    public String ipAddress;

    public UserRequest(){};

    public String getEmail() {
        return email;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public void setEmail(String email) {
        this.email = email;
    }



}
