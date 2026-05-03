package com.example.taskmanagementapi.controller;

import com.example.taskmanagementapi.dto.AuthResponse;
import com.example.taskmanagementapi.dto.LoginRequest;
import com.example.taskmanagementapi.dto.RegisterRequest;
import com.example.taskmanagementapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    // AuthService contains actual register/login logic
    private final AuthService authService;

    // POST /api/auth/register
    // Used to create a new user account
    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // POST /api/auth/login
    // Used to login existing user
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}