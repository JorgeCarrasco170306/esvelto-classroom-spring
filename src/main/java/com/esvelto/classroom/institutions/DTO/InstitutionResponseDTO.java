package com.esvelto.classroom.institutions.DTO;

import java.util.Set;
import java.util.UUID;

public record InstitutionResponseDTO(
        UUID id,
        String name,
        String imageUrl,
        UUID teacherId,
        Set<UUID> studentsIds
) {
}
