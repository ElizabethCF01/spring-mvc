package com.example.spring_boot.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GhRepositoryNotFoundException  extends RuntimeException {
    public GhRepositoryNotFoundException(String message) {
        super(message);
    }
}
