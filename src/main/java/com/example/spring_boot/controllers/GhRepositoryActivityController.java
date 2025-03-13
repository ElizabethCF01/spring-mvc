package com.example.spring_boot.controllers;

import com.example.spring_boot.models.GhRepositoryActivity;
import com.example.spring_boot.services.GhRepositoryActivityManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Repository Activities", description = "Endpoints to get recent activities from repositories")
@RestController
@RequestMapping("/api/activities")
public class GhRepositoryActivityController {

    private final GhRepositoryActivityManager activityRepositoryManager;

    public GhRepositoryActivityController(GhRepositoryActivityManager activityRepositoryManager) {
        this.activityRepositoryManager = activityRepositoryManager;
    }

    @Operation(summary = "Get activities\", description = \"Returns a paginated list of recent activities from all repositories")
    @GetMapping
    public Page<GhRepositoryActivity> getRecentActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));
        return activityRepositoryManager.getRecentActivities(pageable);
    }

    @Operation(summary = "Get activities by repository name\", description = \"Returns a paginated list of recent activities from a repository")
    @ApiResponse(responseCode = "200", description = "Activity list")
    @GetMapping("/repo/{name}")
    public Page<GhRepositoryActivity> getActivitiesByRepoName(
            @Parameter(description = "Repository name") @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate,DESC") String sort)
    {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventDate"));
        return activityRepositoryManager.getActivitiesByRepositoryName(name, pageable);
    }


    @Operation(summary = "Get activities by repository owner\", description = \"Returns a paginated list of recent activities from a repository by owner name")
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

    @Operation(summary = "Get activities by repository owner and name\", description = \"Returns a paginated list of recent activities from a repository by its owner and name")
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
