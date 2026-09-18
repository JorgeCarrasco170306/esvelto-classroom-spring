package com.esvelto.classroom.teachers.DTOS;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TeacherRequest(
        @NotNull(message = "User ID is required")
        UUID userId
) {}