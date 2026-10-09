package com.esvelto.classroom.modules.courses.dto;

import java.util.UUID;

public record CourseInstitutionResponse(
        UUID id,
        String name
) {
}
