package com.esvelto.classroom.institutions.DTOS;


import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record InstitutionRequest
        (
                @NotNull
                String name,
                @NotNull
                String logoUrl,
                @NotNull
                UUID teacherId,
                List<UUID> studentsIds
        ) {
}
