package com.example.taskmanagementapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // secret key from application.properties
    @Value("${jwt.secret}")
    private String secret;

    // token expiration time
    @Value("${jwt.expiration}")
    private Long expiration;

    // 🔹 Generate JWT token using user email
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email) // storing email inside token
                .issuedAt(new Date()) // when token created
                .expiration(new Date(System.currentTimeMillis() + expiration)) // expiry time
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // sign token with secret key
                .compact();
    }

    // 🔹 Extract email from token
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject(); // this gives email
    }
}