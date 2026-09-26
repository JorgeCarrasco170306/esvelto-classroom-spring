package com.esvelto.classroom.curso.DTO;

import java.util.Set;
import java.util.UUID;

public record CursoResponseDTO(
        UUID id,
        String name,
        Set<UUID> institutionsId
) {
}
