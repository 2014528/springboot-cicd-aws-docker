package com.example.taskmanagementapi.repository;

import com.example.taskmanagementapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Repository layer — handles all database operations for User
// Extends JpaRepository which provides built-in methods like save(), findById(), findAll(), delete() for User table
// <User, Long> means → User is the entity, Long is the type of primary key (id)

public interface UserRepository extends JpaRepository<User, Long> {


    // Custom query — Spring auto generates SQL: SELECT * FROM users WHERE email = ?
    // Returns Optional<User> meaning it may or may not find a user (avoids NullPointerException)
    Optional<User> findByEmail(String email);

    // Custom query — Spring auto generates SQL: SELECT COUNT(*) FROM users WHERE email = ?
    // Returns true if email already exists in DB, false if not (used during registration to prevent duplicate emails)

    boolean existsByEmail(String email);
}