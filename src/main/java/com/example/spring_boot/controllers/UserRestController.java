package com.example.spring_boot.controllers;

import com.example.spring_boot.dto.UserRequest;
import com.example.spring_boot.services.UserManager;
import com.example.spring_boot.util.IPAddressUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserManager _userManager;

    public UserRestController(UserManager userManager) {
        this._userManager = userManager;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeEmail(
            @Valid @RequestBody UserRequest userRequest,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        // Check if there are validation errors
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors); // 400 Bad Request
        }

        // Verify if email is duplicated
        if (_userManager.emailExists(userRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body(Collections.singletonMap("error", "This email already exists"));
        }

        // Get client ip
        userRequest.setIpAddress(IPAddressUtils.getClientIpAddress(request));

        _userManager.saveUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(Collections.singletonMap("message", "Thank you for subscribing!"));
    }
}
