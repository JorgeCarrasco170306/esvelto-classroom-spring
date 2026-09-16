package com.esvelto.classroom.institutions.DTOS;


import java.util.List;
import java.util.UUID;

public record InstitutionRequest
        (
                String name,
                String logoUrl,
                UUID teacherId,
                List<UUID> studentsIds
        ) {
}
