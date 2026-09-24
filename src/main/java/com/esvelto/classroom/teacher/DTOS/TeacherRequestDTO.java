package com.esvelto.classroom.teacher.DTOS;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TeacherRequestDTO(
        @NotNull(message = "El ID del usuario es obligatorio")
        UUID userId
) {
}
