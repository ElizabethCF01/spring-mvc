package com.example.spring_boot.controllers;

import com.example.spring_boot.dto.UserRequest;
import com.example.spring_boot.model.UserModel;
import com.example.spring_boot.services.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserManager userManager;

    @InjectMocks
    private UserRestController userRestController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void testSubscribeEmail_Success() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");

        when(userManager.emailExists(userRequest.getEmail())).thenReturn(false);
        doNothing().when(userManager).saveUser(any(UserRequest.class));

        mockMvc.perform(post("/api/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Thank you for subscribing!"));
    }
    @Test
    void testSubscribeEmail_EmailAlreadyExists() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");

        when(userManager.emailExists(userRequest.getEmail())).thenReturn(true);

        mockMvc.perform(post("/api/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("This email already exists"));
    }

    @Test
    void testSubscribeEmail_InvalidRequest() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("");

        mockMvc.perform(post("/api/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testGetSubscribers() throws Exception {
        List<UserModel> mockUsers = Arrays.asList(
                new UserModel(),
                new UserModel()
        );

        when(userManager.getSubscribers()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/subscribers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

}
