package com.example.spring_boot.services;

import com.example.spring_boot.client.GhClient;
import com.example.spring_boot.exeptions.GhRepositoryNotFoundException;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.models.GhRepositoryActivity;
import com.example.spring_boot.repositories.GhRepositoryActivityRepository;
import com.example.spring_boot.repositories.GhRepositoryRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GhRepositoryActivityManager {

    private final GhClient ghClient;
    private final GhRepositoryActivityRepository activityRepository;
    private final GhRepositoryRepository repositoryRepository;;

    public GhRepositoryActivityManager (GhClient ghClient,GhRepositoryActivityRepository activityRepository, GhRepositoryRepository repositoryRepository) {
        this.ghClient = ghClient;
        this.activityRepository = activityRepository;
        this.repositoryRepository = repositoryRepository;
    }

    public String generateEventDescription(Map<String, Object> event) {
        String eventType = (String) event.get("type");

        switch (eventType) {
            case "PushEvent":
                Map<String, Object> payload = (Map<String, Object>) event.get("payload");
                int commits = ((List<?>) payload.getOrDefault("commits", new ArrayList<>())).size();
                return "🚀 Push with " + commits + " commits";

            case "PullRequestEvent":
                Map<String, Object> pr = (Map<String, Object>) event.get("payload");
                String action = (String) pr.get("action");
                return "🔀 Pull Request " + action + " by " + getActor(event);

            case "IssuesEvent":
                Map<String, Object> issue = (Map<String, Object>) event.get("payload");
                return "🐛 Issue " + issue.get("action") + ": " + issue.get("title");

            case "ReleaseEvent":
                Map<String, Object> release = (Map<String, Object>) event.get("payload");
                return "📦 New version: " + release.get("name");

            default:
                return "📢 " + eventType + " in " + getRepositoryName(event);
        }
    }

    private String getActor(Map<String, Object> event) {
        Map<String, Object> actor = (Map<String, Object>) event.get("actor");
        return actor != null ? (String) actor.get("login") : "Unknown";
    }

    private String getRepositoryName(Map<String, Object> event) {
        Map<String, Object> repo = (Map<String, Object>) event.get("repo");
        return repo != null ? (String) repo.get("name") : "Unknown repo";
    }

    @Transactional
    public void fetchAndSaveActivities() {
        List<GhRepository> repositories = repositoryRepository.findAll();

        for (GhRepository repo : repositories) {
            List<Map<String, Object>> events = ghClient.getRepositoryEvents(repo.getOwner(), repo.getName());

            for (Map<String, Object> event : events) {
                String eventId = (String) event.get("id");
                String eventType = (String) event.get("type");

                if (activityRepository.existsByEventId(eventId)) continue;

                GhRepositoryActivity activity = new GhRepositoryActivity();
                activity.setRepository(repo);
                activity.setEventId(eventId);
                activity.setEventType(eventType);
                activity.setDescription(generateEventDescription(event));
                activity.setEventDate(LocalDateTime.now());

                activityRepository.save(activity);
            }
        }
    }

    public List<GhRepositoryActivity> getRecentActivities() {
        return activityRepository.findAll(Sort.by(Sort.Direction.DESC, "eventDate"));
    }

    public List<GhRepositoryActivity> getActivitiesByRepositoryName(String name) {
        GhRepository repository = repositoryRepository.findByName(name)
                .orElseThrow(() -> new GhRepositoryNotFoundException("Repository '" + name + "' not found"));

        return activityRepository.findByRepositoryName(name, Sort.by(Sort.Direction.DESC, "eventDate"));
    }

    public List<GhRepositoryActivity> getActivitiesByOwner(String owner) {
        if (!repositoryRepository.existsByOwner(owner)) {
            throw new GhRepositoryNotFoundException("There are no repositories from owner '" + owner + "'");
        }
        return activityRepository.findByRepositoryOwner(owner, Sort.by(Sort.Direction.DESC, "eventDate"));
    }

    public List<GhRepositoryActivity> getActivitiesByOwnerAndRepo(String owner, String name) {
        GhRepository repository = repositoryRepository.findByOwnerAndName(owner, name)
                .orElseThrow(() -> new GhRepositoryNotFoundException("Repository '" + owner + "/" + name + "' not found"));

        return activityRepository.findByRepositoryOwnerAndRepositoryName(owner, name, Sort.by(Sort.Direction.DESC, "eventDate"));
    }
}

