package com.example.spring_boot.repositories;

import com.example.spring_boot.models.GhRepositoryActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GhRepositoryActivityRepository  extends JpaRepository<GhRepositoryActivity, Long> {
    boolean existsByEventId(String eventId);

    Page<GhRepositoryActivity> findByRepositoryName(String name, Pageable pageable);

    Page<GhRepositoryActivity> findByRepositoryOwner(String owner, Pageable pageable);

    Page<GhRepositoryActivity> findByRepositoryOwnerAndRepositoryName(String owner, String name, Pageable pageable);
}
