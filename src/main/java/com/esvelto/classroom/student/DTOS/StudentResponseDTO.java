package com.esvelto.classroom.student.DTOS;

import java.util.UUID;

public record StudentResponseDTO(
        UUID userId,
        UUID studentId
) {
}
