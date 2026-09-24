package com.esvelto.classroom.student.DTOS;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StudentRequestDTO(
        @NotNull(message = "El ID del usuario es obligatorio")
        UUID userId
) {
}
