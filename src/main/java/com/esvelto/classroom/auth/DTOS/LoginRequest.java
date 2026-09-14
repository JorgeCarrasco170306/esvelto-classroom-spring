package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LoginRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
