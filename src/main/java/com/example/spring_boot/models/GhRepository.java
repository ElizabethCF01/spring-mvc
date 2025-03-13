package com.example.spring_boot.models;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "repositories")
@Getter
@Setter
public class GhRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String githubUrl;
    @NotNull
    private String name;
    @NotNull
    private String owner;
    private LocalDateTime lastChecked;

}
