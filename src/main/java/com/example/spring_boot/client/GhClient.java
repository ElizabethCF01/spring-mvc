package com.example.spring_boot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "githubClient", url = "https://api.github.com")
public interface GhClient {

    @GetMapping("/repos/{owner}/{repo}")
    ResponseEntity<String> checkRepositoryExists(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/events")
    List<Map<String, Object>> getRepositoryEvents(@PathVariable String owner, @PathVariable String repo);
}
