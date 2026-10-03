package com.esvelto.classroom.modules.institutions.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstitutionRequest {
    List<UUID> students;
    @NotNull 
    UUID teacherId;
    @NotBlank
    String name;
    @NotBlank
    String imageUrl;
}
