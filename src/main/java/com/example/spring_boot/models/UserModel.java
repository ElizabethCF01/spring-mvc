package com.example.spring_boot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "ip_address")
    private String ipAddress;

    private Date createdAt;

    @Override
    public String toString() {
        return "EmailSubscription{" +
                "email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", subscriptionDate=" + createdAt +
                '}';
    }
}
