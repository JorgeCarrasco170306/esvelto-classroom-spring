package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterDTO(
        @NotBlank
        @Length(min = 4, message = "name length must be > 4")
        String name,
        @NotBlank
        @Length(min = 4, message = "lastname length must be > 4")
        String lastname,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 8, message = "Password length must be > 8")
        String password
) {
}
