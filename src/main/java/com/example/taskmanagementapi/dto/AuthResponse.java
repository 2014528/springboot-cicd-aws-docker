package com.example.taskmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    // this will store the JWT token we send back after login/register
    private String token;
}