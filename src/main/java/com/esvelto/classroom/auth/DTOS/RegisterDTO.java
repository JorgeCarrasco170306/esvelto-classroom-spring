package com.esvelto.classroom.auth.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 4, message = "El nombre debe tener al menos 4 caracteres")
        String name,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 4, message = "El apellido debe tener al menos 4 caracteres")
        String lastname,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {
}
