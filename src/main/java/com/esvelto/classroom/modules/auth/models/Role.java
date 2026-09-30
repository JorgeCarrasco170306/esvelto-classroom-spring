package com.esvelto.classroom.modules.auth.models;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    STUDENT, 
    TEACHER, 
    ADMIN;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + name();
    }
}
