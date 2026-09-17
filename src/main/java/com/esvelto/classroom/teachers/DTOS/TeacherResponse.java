package com.esvelto.classroom.teachers.DTOS;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record TeacherResponse(
        UUID id,
        String teacherCode,
        List<UUID> institutionIds,
        UUID userId
) {
}