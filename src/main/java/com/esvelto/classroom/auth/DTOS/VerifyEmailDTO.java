package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.NotBlank;

public record VerifyEmailDTO(
        String code
) {
}
