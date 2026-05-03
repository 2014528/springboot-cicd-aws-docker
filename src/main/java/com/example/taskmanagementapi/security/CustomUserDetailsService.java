package com.example.taskmanagementapi.security;

import com.example.taskmanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // Used to fetch user data from database
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        // Find user by email, otherwise throw error
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Convert our User entity into Spring Security's UserDetails object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // login username will be email
                .password(user.getPassword()) // encrypted password from DB
                .roles(user.getRole().name().replace("ROLE_", "")) // ROLE_USER becomes USER
                .build();
    }
}