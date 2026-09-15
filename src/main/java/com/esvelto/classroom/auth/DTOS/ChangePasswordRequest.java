package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ChangePasswordRequest(

        @NotBlank
        String currentPassword,
        @NotBlank
        String newPassword,
        @NotBlank
        String confirmPassword
) {
}
