package com.esvelto.classroom.modules.auth.services;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String sendVerificationEmail(String destination) {

        User user = userRepository.findByEmail(destination)
                .orElseThrow(() -> GlobalError.NotFound("user not found"));

        String verificationCode = RandomStringUtils.secure().nextNumeric(6);

        user.setVerificationCode(verificationCode);
        user.setExpirationDate(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);

        Email email = new Email(
                destination,
                "Código de verificación: " + verificationCode,
                """
                        ¡Hola!

                        Tu código de verificación para Esvelto Classroom es: %s

                        Ingresa este código en la aplicación para completar tu registro.
                        Este código expira en 15 minutos.

                        Si no creaste esta cuenta, ignora este correo.
                        """.formatted(verificationCode));

        emailService.sendSimpleEmail(email);

        return verificationCode;
    }

    @Transactional
    public void validateEmail(ValidateEmail dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> GlobalError.NotFound("There is no user with this email"));

        if (user.isVerified()) {
            throw GlobalError.BadRequest("User already verified");
        }

        if (user.getVerificationCode() == null
                || !Objects.equals(user.getVerificationCode(), dto.verificationCode())) {
            throw GlobalError.BadRequest("incorrect code");
        }

        if (user.getExpirationDate() == null
                || !user.getExpirationDate().isAfter(LocalDateTime.now())) {
            throw GlobalError.BadRequest(
                    "verification code expired");
        }

        user.setVerified(true);
        user.setVerificationCode(null);
        user.setExpirationDate(null);

        userRepository.saveAndFlush(user);
    }

    public void register(RegisterRequest dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw GlobalError.Conflict("email already exists");
        }

        User user = authMapper.toEntity(dto);

        if (user == null) {
            throw GlobalError.BadRequest("user could not be created");
        }

        user.setRole(Role.STUDENT);
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(dto.password()));

        userRepository.save(user);

        sendVerificationEmail(dto.email());
    }

    public LoginResponse login(LoginRequest dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> GlobalError.NotFound("user not found"));

        if (!user.isVerified()) {
            throw GlobalError.Conflict("User is not verified");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
