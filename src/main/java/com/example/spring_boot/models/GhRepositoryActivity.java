package com.example.spring_boot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Getter
@Setter
public class GhRepositoryActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GhRepository repository;

    private String eventType; // "commit", "pull_request", "issue", "release"
    private String eventId;
    private String description;
    private String url;
    private LocalDateTime eventDate;
}
