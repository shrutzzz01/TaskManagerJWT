package com.taskmanager.dto;

public record TaskTypeResponse(
        Long id,
        String taskType,
        String createdByUser
)
{}
