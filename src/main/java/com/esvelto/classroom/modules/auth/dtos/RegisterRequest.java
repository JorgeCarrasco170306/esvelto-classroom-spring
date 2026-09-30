package com.esvelto.classroom.modules.auth.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
                @NotBlank(message = "missing name") String name,
                @NotBlank(message = "missing lastname") String lastname,
                @NotBlank(message = "missing email") @Email(message = "this is not a email") String email,
                @NotBlank(message = "missing password") @Length(min = 8, message = "password must have 8 min characters") String password) {
}
