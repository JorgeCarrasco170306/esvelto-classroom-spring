package com.esvelto.classroom.modules.institutions.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstitutionRequest {
    @NotNull 
    UUID teacherId;
    @NotBlank
    String name;
    @NotBlank
    String imageUrl;
}
