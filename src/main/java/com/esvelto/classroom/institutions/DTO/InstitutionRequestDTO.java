package com.esvelto.classroom.institutions.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record InstitutionRequestDTO(
        @NotBlank(message = "El nombre de la institución es obligatorio")
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String name
) {
}
