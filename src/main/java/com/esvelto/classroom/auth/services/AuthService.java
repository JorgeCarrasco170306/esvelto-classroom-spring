package com.esvelto.classroom.auth.services;

import com.esvelto.classroom.auth.DTOS.LoginDTO;
import com.esvelto.classroom.auth.DTOS.LoginResponseDTO;
import com.esvelto.classroom.auth.DTOS.RegisterDTO;
import com.esvelto.classroom.auth.DTOS.UserMapper;
import com.esvelto.classroom.auth.DTOS.UserResponseDTO;
import com.esvelto.classroom.auth.models.Role;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.files.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StorageService storageService;

    // TODO : implement verify-email and password change

    @Transactional
    public String updateAvatar(UUID userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getProfilePhotoUrl() != null) {
            storageService.deleteFile(user.getProfilePhotoUrl());
        }

        String userAvatar = storageService.uploadFile(file, "esvelto/avatars");
        user.setProfilePhotoUrl(userAvatar);
        userRepository.save(user);

        return userAvatar;
    }

    public LoginResponseDTO login(LoginDTO dto) {
        var authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.email(), dto.password()
                        )
                );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        UserResponseDTO userResponse = userMapper.toDto(user);
        return new LoginResponseDTO(token, userResponse);
    }

    public void register(RegisterDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already registered"
            );
        }

        User user = userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);
        userRepository.save(user);
    }

    public UserResponseDTO getMe(User principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toDto(user);
    }

}
