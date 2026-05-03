package com.example.taskmanagementapi.service;

import com.example.taskmanagementapi.dto.AuthResponse;
import com.example.taskmanagementapi.dto.LoginRequest;
import com.example.taskmanagementapi.dto.RegisterRequest;
import com.example.taskmanagementapi.entity.Role;
import com.example.taskmanagementapi.entity.User;
import com.example.taskmanagementapi.repository.UserRepository;
import com.example.taskmanagementapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    // talks to users table in database
    private final UserRepository userRepository;

    // converts plain password into encrypted password
    private final PasswordEncoder passwordEncoder;

    // creates JWT token
    private final JwtService jwtService;

    // checks email and password during login
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        // check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // create new user object
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // encrypt password
                .role(Role.ROLE_USER) // default role is USER
                .build();

        // save user in database
        userRepository.save(user);

        // generate token after successful registration
        String token = jwtService.generateToken(user.getEmail());

        // send token back
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        // Spring Security checks email and password here
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // if login successful, generate token
        String token = jwtService.generateToken(request.getEmail());

        // send token back
        return new AuthResponse(token);
    }
}