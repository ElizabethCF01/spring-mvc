package com.example.spring_boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    public String email;
    public String ipAddress;

    public UserRequest(){};

}
