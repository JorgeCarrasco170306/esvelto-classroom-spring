package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 8)
        String password,
        @NotBlank
        @Size(min = 3)
        String name,
        @Size(min = 3)
        String lastname,
        @NotBlank
        LocalDate birthdate
) {
}
