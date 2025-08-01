package com.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username is required") @Size(max=20)
        String username,
        @NotBlank(message = "Email is required") @Size(max=50)
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Password can not be null")
        @Size(min=6, max=20, message = "Password should be between 6 to 20 characters")
        String password
)
{}
