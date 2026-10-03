package com.esvelto.classroom.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record ResendVerification(
        @NotBlank String email
) {

}
