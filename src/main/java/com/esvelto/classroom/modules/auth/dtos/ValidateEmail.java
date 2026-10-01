package com.esvelto.classroom.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record ValidateEmail(
                @NotBlank String verificationCode,
                @NotBlank String email) {

}
