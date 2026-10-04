package com.esvelto.classroom.modules.institutions.dtos;

import java.util.UUID;

public record InstitutionStudentResponse(
        UUID studentId,
        UUID userId,
        String name,
        String lastname,
        String email
) {
}
