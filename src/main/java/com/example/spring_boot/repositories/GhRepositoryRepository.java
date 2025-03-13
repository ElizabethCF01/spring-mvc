package com.example.spring_boot.repositories;

import com.example.spring_boot.models.GhRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GhRepositoryRepository  extends JpaRepository<GhRepository, Integer> {

    Optional<GhRepository> findByOwnerAndName(String owner, String name);
}
