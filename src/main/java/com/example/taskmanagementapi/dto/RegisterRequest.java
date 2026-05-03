package com.example.taskmanagementapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO (Data Transfer Object) — carries registration data sent by user in API request body
// This is NOT a database entity, it just holds the data coming from outside (Postman or frontend)
@Data // Lombok: auto generates getters, setters, toString, equals, hashCode
public class RegisterRequest {

    @NotBlank // validation — name field cannot be empty or null, returns 400 error if violated
    private String name;

    @Email   // validation — must be a valid email format like user@example.com
    @NotBlank // validation — email field cannot be empty or null
    private String email;

    @NotBlank // validation — password field cannot be empty or null
    private String password;
}