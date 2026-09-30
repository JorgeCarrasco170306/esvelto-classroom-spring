package com.esvelto.classroom.modules.auth.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.auth.dtos.AuthMapper;
import com.esvelto.classroom.modules.auth.dtos.LoginRequest;
import com.esvelto.classroom.modules.auth.dtos.LoginResponse;
import com.esvelto.classroom.modules.auth.dtos.RegisterRequest;
import com.esvelto.classroom.modules.auth.models.Role;
import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest dto) {

        if (userRepository.existsByEmail(dto.email()))
            throw GlobalError.Conflict("email already exists");

        String code = RandomStringUtils.secure().nextNumeric(6);

        User user = authMapper.toEntity(dto);

        if (user == null) {
            throw GlobalError.BadRequest("user could not be created");
        }

        user.setRole(Role.STUDENT);
        user.setVerificationCode(code);
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(dto.password()));

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> GlobalError.NotFound("user not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);

    }

}
