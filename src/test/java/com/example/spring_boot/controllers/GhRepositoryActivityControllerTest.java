package com.example.spring_boot.controllers;

import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.models.GhRepositoryActivity;
import com.example.spring_boot.services.GhRepositoryActivityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class GhRepositoryActivityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GhRepositoryActivityManager activityRepositoryManager;

    @InjectMocks
    private GhRepositoryActivityController activityController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }
    @Test
    void testGetRecentActivities() throws Exception {
        // 📌 Test data
        GhRepository repo1 = new GhRepository();
        repo1.setId(1L);
        repo1.setOwner("octocat");
        repo1.setName("Hello-World");

        GhRepository repo2 = new GhRepository();
        repo2.setId(2L);
        repo2.setOwner("spring");
        repo2.setName("spring-boot");

        GhRepositoryActivity activity1 = new GhRepositoryActivity();
        activity1.setEventDate(LocalDateTime.now());
        activity1.setEventType("PushEvent");
        activity1.setDescription("Push to Hello-World");
        activity1.setRepository(repo1);

        GhRepositoryActivity activity2 = new GhRepositoryActivity();
        activity2.setEventDate(LocalDateTime.now().minusDays(1));
        activity2.setEventType("PullRequestEvent");
        activity2.setDescription("Pull request on spring-boot");
        activity2.setRepository(repo2);

        List<GhRepositoryActivity> activities = List.of(activity1, activity2);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("eventDate")));
        Page<GhRepositoryActivity> page = new PageImpl<>(activities, pageable, activities.size());

        // 🛠️ Mock manager logic with matching Pageable
        when(activityRepositoryManager.getRecentActivities(pageable)).thenReturn(page);

        // 🛠️ Execute GET
        mockMvc.perform(get("/api/activities")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "eventDate,DESC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))  // Verifica que hay 2 actividades
                .andExpect(jsonPath("$.content[0].eventType").value("PushEvent"))
                .andExpect(jsonPath("$.content[1].eventType").value("PullRequestEvent"))
                .andExpect(jsonPath("$.content[0].repository.owner").value("octocat"))
                .andExpect(jsonPath("$.content[1].repository.owner").value("spring"));
    }

}

