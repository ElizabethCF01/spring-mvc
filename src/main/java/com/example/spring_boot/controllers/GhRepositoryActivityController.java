package com.example.spring_boot.controllers;

import com.example.spring_boot.models.GhRepositoryActivity;
import com.example.spring_boot.services.GhRepositoryActivityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/activities")
public class GhRepositoryActivityController {

    private final GhRepositoryActivityManager activityRepositoryManager;

    public GhRepositoryActivityController(GhRepositoryActivityManager activityRepositoryManager) {
        this.activityRepositoryManager = activityRepositoryManager;
    }

    @GetMapping
    public Page<GhRepositoryActivity> getRecentActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));
        return activityRepositoryManager.getRecentActivities(pageable);
    }

    @GetMapping("/repo/{name}")
    public Page<GhRepositoryActivity> getActivitiesByRepoName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort)
    {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));
        return activityRepositoryManager.getActivitiesByRepositoryName(name, pageable);
    }


    @GetMapping("/owner/{owner}")
    public Page<GhRepositoryActivity> getActivitiesByOwner(
            @PathVariable String owner,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));

        return activityRepositoryManager.getActivitiesByOwner(owner, pageable);
    }


    @GetMapping("/owner/{owner}/repo/{name}")
    public Page<GhRepositoryActivity> getActivitiesByOwnerAndRepo(
            @PathVariable String owner,
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));
        return activityRepositoryManager.getActivitiesByOwnerAndRepo(owner, name, pageable);
    }
}
