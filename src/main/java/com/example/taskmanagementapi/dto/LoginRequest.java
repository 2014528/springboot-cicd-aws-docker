package com.example.taskmanagementapi.dto;

import lombok.Data;

@Data  // Lombok: auto generates getters, setters, toString, equals, hashCode.
public class LoginRequest {

    // user's email for login
    private String email;

    // user's password for login
    private String password;
}