package com.esvelto.classroom.auth.controllers;

import com.esvelto.classroom.auth.DTOS.*;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest dto) {
        var userResponse = authService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest dto) {
        return ResponseEntity.ok(authService.login(dto));
    }


    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest dto,
                                               @AuthenticationPrincipal User user) {
        authService.changePassword(dto, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@Valid @RequestBody VerifyEmailDTO dto,
                                            @RequestParam UUID userId) {
        authService.verifyEmail(userId, dto.code());
        return ResponseEntity.ok().build();
    }

}
