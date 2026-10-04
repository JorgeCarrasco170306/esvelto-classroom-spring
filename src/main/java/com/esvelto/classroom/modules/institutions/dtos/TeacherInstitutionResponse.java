package com.esvelto.classroom.modules.institutions.dtos;

import java.util.UUID;

public record TeacherInstitutionResponse(
        UUID teacherId,
        UUID userId,
        String name,
        String lastname,
        String email
) {
}
