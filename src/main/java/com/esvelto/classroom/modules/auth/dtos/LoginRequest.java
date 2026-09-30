package com.esvelto.classroom.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank (message = "missing email")
        String email,
        @NotBlank (message = "missing password")
        String password) {

}
