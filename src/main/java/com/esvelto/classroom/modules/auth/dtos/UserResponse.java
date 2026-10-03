package com.esvelto.classroom.modules.auth.dtos;

import com.esvelto.classroom.modules.auth.models.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String lastname,
        Role role
) {}
