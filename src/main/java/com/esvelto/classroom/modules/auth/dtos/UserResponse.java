package com.esvelto.classroom.modules.auth.dtos;

import java.util.UUID;

import com.esvelto.classroom.modules.auth.models.Role;
import com.esvelto.classroom.modules.auth.models.User;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String lastname,
        Role role
) {
    public static UserResponse from(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLastname(),
                user.getRole()
        );
    }
}
