package com.example.spring_boot.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GhRepositoryRequest {
    @NotNull
    private String name;

    @NotNull
    private String owner;
}
