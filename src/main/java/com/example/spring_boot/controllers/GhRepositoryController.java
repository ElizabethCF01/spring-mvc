package com.example.spring_boot.controllers;

import com.example.spring_boot.dtos.GhRepositoryRequest;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.services.GhRepositoryManager;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repositories")
public class GhRepositoryController {

    private final GhRepositoryManager _ghRepositoryManager;

    public GhRepositoryController(GhRepositoryManager ghRepositoryManager) {
        _ghRepositoryManager = ghRepositoryManager;
    }

    @PostMapping
    public ResponseEntity<GhRepository> registerRepository(@RequestBody @Valid GhRepositoryRequest dto) {
        GhRepository repo = _ghRepositoryManager.addGhRepository(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo);
    }

    @GetMapping
    public List<GhRepository> getTrackedRepositories() {
        return _ghRepositoryManager.getAllTrackedRepositories();
    }
}
