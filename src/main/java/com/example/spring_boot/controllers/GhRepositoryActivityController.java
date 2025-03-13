package com.example.spring_boot.controllers;

import com.example.spring_boot.models.GhRepositoryActivity;
import com.example.spring_boot.services.GhRepositoryActivityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class GhRepositoryActivityController {

    private final GhRepositoryActivityManager activityRepositoryManager;

    public GhRepositoryActivityController(GhRepositoryActivityManager activityRepositoryManager) {
        this.activityRepositoryManager = activityRepositoryManager;
    }

    @GetMapping
    public List<GhRepositoryActivity> getRecentActivities() {
        return activityRepositoryManager.getRecentActivities();
    }

    @GetMapping("/repo/{name}")
    public List<GhRepositoryActivity> getActivitiesByRepoName(@PathVariable String name) {
        return activityRepositoryManager.getActivitiesByRepositoryName(name);
    }


    @GetMapping("/owner/{owner}")
    public List<GhRepositoryActivity> getActivitiesByOwner(@PathVariable String owner) {
        return activityRepositoryManager.getActivitiesByOwner(owner);
    }


    @GetMapping("/owner/{owner}/repo/{name}")
    public List<GhRepositoryActivity> getActivitiesByOwnerAndRepo(
            @PathVariable String owner,
            @PathVariable String name) {
        return activityRepositoryManager.getActivitiesByOwnerAndRepo(owner, name);
    }
}
