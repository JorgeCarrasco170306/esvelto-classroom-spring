package com.esvelto.classroom.modules.courses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseRequest(
        @NotBlank
        String name,
        @NotNull
        UUID institutionId
) {
}
