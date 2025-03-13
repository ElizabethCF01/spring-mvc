package com.example.spring_boot.scheduler;

import com.example.spring_boot.services.GhRepositoryActivityManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RepositoryPollingTask {

    private final GhRepositoryActivityManager repositoryActivityManager;

    public RepositoryPollingTask ( GhRepositoryActivityManager repositoryActivityManager) {
        this.repositoryActivityManager = repositoryActivityManager;
    }

    @Scheduled(fixedRate = 300000) // Every 5 minutes (300,000 ms)
    public void pollGitHubEvents() {
        System.out.println("⏳ Checking GitHub repositories for new events...");
        repositoryActivityManager.fetchAndSaveActivities();
    }
}
