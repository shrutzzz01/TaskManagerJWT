package com.taskmanager.dto;

import com.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Title is required") @Size(max=20)
        String title,
        @Size(max=40)
        String description,
        LocalDate dueDate,
        TaskTypeRequest taskType,
        TaskStatus status
)
{}
