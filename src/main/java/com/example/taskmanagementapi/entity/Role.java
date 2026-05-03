package com.example.taskmanagementapi.entity;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN
}
//enum here because we have only two roles and it is easier to manage them as an enum rather than a separate entity.
// It also helps to ensure that only valid roles are assigned to users.