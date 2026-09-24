package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {
}
