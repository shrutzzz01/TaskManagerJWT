package com.taskmanager.dto;

import com.taskmanager.model.TaskStatus;

import java.time.LocalDate;

public record TaskResponse (
    Long id,
    String title,
    String description,
    LocalDate dueDate,
    String taskType,
    TaskStatus status
)
{}
