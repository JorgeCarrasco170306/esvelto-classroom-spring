package com.esvelto.classroom.teacher.DTOS;

import java.util.UUID;

public record TeacherResponseDTO(
        UUID id,
        UUID userId
) {
}
