package com.esvelto.classroom.auth.DTOS;

import com.esvelto.classroom.auth.models.Role;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String lastname,
        String email,
        Role role,
        String profilePhotoUrl
) {
}
