package com.esvelto.classroom.common.utils.models;

import com.esvelto.classroom.auth.models.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditorAware implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.empty(); // O Optional.of(0L) para procesos del sistema
        }

        // Asumiendo que tu UserDetails personalizado se llama 'UsuarioPrincipal' o similar y tiene un getId()
        Object principal = authentication.getPrincipal();

        if (principal instanceof User usuarioDetails) {
            return Optional.of(usuarioDetails.getId());
        }

        return Optional.empty();
    }
}
