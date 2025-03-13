package com.example.spring_boot.services;

import com.example.spring_boot.dtos.GhRepositoryRequest;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.repositories.GhRepositoryRepository;
import com.example.spring_boot.client.GhClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GhRepositoryManager {
    private final GhRepositoryRepository _ghRepositoryRepository;

    private final GhClient _githubClient;

    public GhRepositoryManager(GhRepositoryRepository ghRepositoryRepository, GhClient githubClient) {
        _ghRepositoryRepository = ghRepositoryRepository;
        _githubClient = githubClient;
    }

    public GhRepository addGhRepository(GhRepositoryRequest dto) {

        try {
            _githubClient.checkRepositoryExists(dto.getOwner(), dto.getName());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This repository does not exist on GitHub ");
        }

        Optional<GhRepository> existingRepo = _ghRepositoryRepository.findByOwnerAndName(dto.getOwner(), dto.getName());
        if (existingRepo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This repository already exists");
        }

        // Create entity
        GhRepository repository = new GhRepository();
        repository.setName(dto.getName());
        repository.setOwner(dto.getOwner());
        repository.setGithubUrl("https://github.com/" + dto.getOwner() + "/" + dto.getName());
        repository.setLastChecked(LocalDateTime.now());

        return _ghRepositoryRepository.save(repository);
    }

    public List<GhRepository> getAllTrackedRepositories() {
        return _ghRepositoryRepository.findAll();
    }

}
