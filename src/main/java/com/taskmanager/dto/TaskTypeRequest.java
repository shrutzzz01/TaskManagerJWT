package com.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskTypeRequest(
        @NotBlank @Size(max = 30)
        String taskType
)
{}
