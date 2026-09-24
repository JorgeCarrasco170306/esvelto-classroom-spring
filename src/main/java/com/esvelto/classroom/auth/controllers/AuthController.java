package com.esvelto.classroom.auth.controllers;

import com.esvelto.classroom.auth.DTOS.LoginDTO;
import com.esvelto.classroom.auth.DTOS.LoginResponseDTO;
import com.esvelto.classroom.auth.DTOS.RegisterDTO;
import com.esvelto.classroom.auth.DTOS.UserResponseDTO;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(authService.getMe(user));
    }

    @PostMapping(
            value = "/{id}/avatar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Map<String, String>> uploadAvatar(
            @PathVariable UUID id,
            @RequestPart("file") MultipartFile file
    ) {
        String avatarUrl = authService.updateAvatar(id, file);

        return ResponseEntity.ok(
                Map.of("avatarUrl", avatarUrl)
        );
    }

}
