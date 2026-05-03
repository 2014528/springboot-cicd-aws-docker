package com.example.taskmanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity   // → tells Spring "this class is a database table
@Table(name = "users")  // → table name in DB will be "users"
@Getter                   // → Lombok: auto creates getters for all fields
@Setter                   // → Lombok: auto creates setters for all fields
@NoArgsConstructor        // → Lombok: creates empty constructor User()
@AllArgsConstructor       // → Lombok: creates constructor with all fields
@Builder                  // → Lombok: allows User.builder().name("John").build()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}

//lombok is used to reduce boilerplate code for getters, setters, constructors, and builders.
// The User entity represents a user in the system with fields for id, name, email, password, and role.
// The email field is marked as unique to ensure that no two users can have the same email address.
// The role field is an enum that defines the user's role in the system, which can be either ROLE_USER or ROLE_ADMIN.
