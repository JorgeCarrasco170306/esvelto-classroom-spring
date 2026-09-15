package com.esvelto.classroom.auth.services;

import com.esvelto.classroom.auth.DTOS.*;
import com.esvelto.classroom.auth.models.Role;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.email.services.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Transactional
    public UserResponse register(RegisterRequest dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already in use");
        }

        String code = CodeService.generateCode();

        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setBirthdate(dto.birthdate());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.STUDENT);
        user.setVerified(false);
        user.setVerificationCode(code);
        user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(10));

        User saved = userRepository.save(user);

        sendCode(user.getId());
        return new UserResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getName(),
                saved.getLastname(),
                saved.getBirthdate(),
                saved.getRole().name(),
                "We've sent a confirmation email"
        );
    }

    @Transactional

    public void verifyEmail(UUID id, String code) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        if (!user.getVerificationCode().equals(code))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Incorrect Code");

        if (!user.isVerified())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already verified");

        if (user.getCodeExpirationTime().isBefore(LocalDateTime.now()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code expired");

        user.setVerified(true);
        user.setVerificationCode(null);
        user.setCodeExpirationTime(null);
        userRepository.save(user);

    }

    @Transactional
    public LoginResponse login(LoginRequest dto) {

        User user = userRepository.findByEmail(dto.email()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
        );

        if (!user.isVerified())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user is not verified");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.email(),
                            dto.password()
                    )
            );

            return new LoginResponse(jwtService.generateToken(user));
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password incorrect");
        }
    }

    @Transactional
    public void changePassword(ChangePasswordRequest dto, User currentUser) {

        if (!dto.newPassword().equals(dto.confirmPassword())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "new password and confirm password are different");
        }

        if (passwordEncoder.matches(dto.currentPassword(), currentUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "current password incorrect");
        }

        if (passwordEncoder.matches(dto.currentPassword(), dto.newPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new password has to be different from earlier password");
        }

        currentUser.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(currentUser);

    }

    @Transactional
    public void sendCode(UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
        );

        if (user.getLastCodeSentAt() != null) {
            int COOLDOWN_MINUTES = 10;
            LocalDateTime nextSend = user.getLastCodeSentAt().plusMinutes(COOLDOWN_MINUTES);

            if (LocalDateTime.now().isBefore(nextSend)) {
                long secondsRest = Duration.between(LocalDateTime.now(), nextSend).getSeconds();


                throw new ResponseStatusException(
                        HttpStatus.TOO_MANY_REQUESTS,
                        "need wait " + secondsRest + " seconds to request another code"
                );

            }
        }

        String newCode = CodeService.generateCode();
        user.setVerificationCode(newCode);
        user.setCodeExpirationTime(LocalDateTime.now().plusMinutes(10));
        user.setLastCodeSentAt(LocalDateTime.now());

        userRepository.save(user);

        emailService.enviarCodigoVerificacion(user.getEmail(), newCode);
    }

}
