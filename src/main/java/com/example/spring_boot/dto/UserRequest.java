package com.example.spring_boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
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
