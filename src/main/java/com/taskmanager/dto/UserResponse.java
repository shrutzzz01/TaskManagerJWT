package com.taskmanager.dto;

public record UserResponse(
        Long userId,
        String username,
        String email
)
{}
