package com.example.spring_boot.controllers;

import com.example.spring_boot.dtos.GhRepositoryRequest;
import com.example.spring_boot.models.GhRepository;
import com.example.spring_boot.repositories.GhRepositoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GhRepositoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GhRepositoryRepository ghRepositoryRepository;

    @Test
    void testRegisterRepository_Success() throws Exception {
        // 📌 Test Data
        GhRepositoryRequest ghRepositoryRequest = new GhRepositoryRequest();
        ghRepositoryRequest.setOwner("octocat");
        ghRepositoryRequest.setName("Hello-World");

        // 🛠️ Execute POST request
        mockMvc.perform(post("/api/repositories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ghRepositoryRequest)))
                .andExpect(status().isCreated())  // Assert HTTP Status 201 (Created)
                .andExpect(jsonPath("$.owner").value("octocat"))  // Assert Owner Field
                .andExpect(jsonPath("$.name").value("Hello-World"));  // Assert Name Field

        Optional<GhRepository> savedRepo = ghRepositoryRepository.findByOwnerAndName("octocat", "Hello-World");
        assert savedRepo.isPresent();
        assert savedRepo.get().getOwner().equals("octocat");
        assert savedRepo.get().getName().equals("Hello-World");
    }
}

