package com.example.spring_boot.controllers;


import com.example.spring_boot.dtos.GhRepositoryRequest;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.services.GhRepositoryManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GhRepositoryControllerTest {
    private MockMvc mockMvc;

    @Mock
    private GhRepositoryManager ghRepositoryManager;

    @InjectMocks
    private GhRepositoryController ghRepositoryController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ghRepositoryController).build();
    }

    @Test
    void testRegisterRepository_Success() throws Exception {
        // 📌 Test data
        GhRepositoryRequest request = new GhRepositoryRequest();
        request.setOwner("octocat");
        request.setName("Hello-World");

        GhRepository mockRepo = new GhRepository();
        mockRepo.setId(1L);
        mockRepo.setOwner("octocat");
        mockRepo.setName("Hello-World");

        // 🛠️ Mock manager logic
        when(ghRepositoryManager.addGhRepository(any(GhRepositoryRequest.class))).thenReturn(mockRepo);

        // 🛠️ Execute POST
        mockMvc.perform(post("/api/repositories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.owner").value("octocat"))
                .andExpect(jsonPath("$.name").value("Hello-World"));
    }

    @Test
    void testGetTrackedRepositories() throws Exception {
        // 📌 Test data
        GhRepository repo1 = new GhRepository();
        repo1.setId(1L);
        repo1.setOwner("octocat");
        repo1.setName("Hello-World");

        GhRepository repo2 = new GhRepository();
        repo2.setId(2L);
        repo2.setOwner("spring");
        repo2.setName("spring-boot");

        List<GhRepository> repositories = List.of(repo1, repo2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<GhRepository> page = new PageImpl<>(repositories, pageable, repositories.size());

        // 🛠️ Mock manager logic
        when(ghRepositoryManager.getAllTrackedRepositories(pageable)).thenReturn(page);

        // 🛠️ Execute GET
        mockMvc.perform(get("/api/repositories")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].owner").value("octocat"))
                .andExpect(jsonPath("$.content[1].owner").value("spring"));
    }

}
