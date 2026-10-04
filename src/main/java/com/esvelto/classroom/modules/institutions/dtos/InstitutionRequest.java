package com.esvelto.classroom.modules.institutions.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionRequest {

    @NotNull
    private UUID userId;

    @NotBlank
    private String name;

    @NotBlank
    private String imageUrl;
}
