package com.example.spring_boot.controllers;

import com.example.spring_boot.dtos.GhRepositoryRequest;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.services.GhRepositoryManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public Page<GhRepository> getTrackedRepositories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return _ghRepositoryManager.getAllTrackedRepositories(pageable);
    }
}
