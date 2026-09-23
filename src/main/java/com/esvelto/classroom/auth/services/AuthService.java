package com.esvelto.classroom.auth.services;

import com.esvelto.classroom.auth.DTOS.LoginDTO;
import com.esvelto.classroom.auth.DTOS.LoginResponseDTO;
import com.esvelto.classroom.auth.DTOS.RegisterDTO;
import com.esvelto.classroom.auth.DTOS.UserMapper;
import com.esvelto.classroom.auth.models.Role;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginDTO dto) {
        var authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.email(), dto.password()
                        )
                );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new LoginResponseDTO(token);
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

}
