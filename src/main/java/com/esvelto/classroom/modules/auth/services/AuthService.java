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
import com.esvelto.classroom.modules.auth.dtos.ValidateEmail;
import com.esvelto.classroom.modules.auth.models.Role;
import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.auth.repository.UserRepository;
import com.esvelto.classroom.modules.email.models.Email;
import com.esvelto.classroom.modules.email.services.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public void validateEmail(ValidateEmail validateEmail) {
        
    }

    public void register(RegisterRequest dto) {

        if (userRepository.existsByEmail(dto.email()))
            throw GlobalError.Conflict("email already exists");

        String verificationCode = RandomStringUtils.secure().nextNumeric(6);

        User user = authMapper.toEntity(dto);

        if (user == null) {
            throw GlobalError.BadRequest("user could not be created");
        }

        // send email

        Email email = new Email(
                dto.email(),
                "Código de verificación: " + verificationCode,
                """
                        ¡Hola!

                        Tu código de verificación para Esvelto Classroom es: %s

                        Ingresa este código en la aplicación para completar tu registro.

                        Si no creaste esta cuenta, ignora este correo.
                        """.formatted(verificationCode));
        emailService.sendSimpleEmail(email);

        user.setRole(Role.STUDENT);
        user.setVerificationCode(verificationCode);
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
