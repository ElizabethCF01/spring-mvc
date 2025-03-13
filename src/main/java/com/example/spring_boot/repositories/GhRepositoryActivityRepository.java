package com.example.spring_boot.repositories;

import com.example.spring_boot.models.GhRepositoryActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhRepositoryActivityRepository  extends JpaRepository<GhRepositoryActivity, Long> {
    boolean existsByEventId(String eventId);

    List<GhRepositoryActivity> findByRepositoryName(String name, Sort sort);

    List<GhRepositoryActivity> findByRepositoryOwner(String owner, Sort sort);

    List<GhRepositoryActivity> findByRepositoryOwnerAndRepositoryName(String owner, String name, Sort sort);
}
