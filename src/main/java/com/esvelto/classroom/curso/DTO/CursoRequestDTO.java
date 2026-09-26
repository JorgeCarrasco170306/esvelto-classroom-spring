package com.esvelto.classroom.curso.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record CursoRequestDTO(
        @NotBlank(message = "course name is required")
        String name,
        @NotEmpty(message = "at least one institution is required")
        Set<UUID> institutionsIds
) {
}
