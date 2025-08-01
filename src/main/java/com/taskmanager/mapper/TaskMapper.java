package com.taskmanager.mapper;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.model.TaskType;
import com.taskmanager.model.User;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TaskMapper {
    public static Task toEntity(TaskRequest dto, User user, TaskType taskType){
        Task t=new Task();
        t.setTitle(dto.title());
        t.setDescription(dto.description());
        t.setDueDate(dto.dueDate());
        t.setStatus(dto.status());
        t.setUser(user);
        t.setTaskType(taskType);
        return t;
    }
    public TaskResponse toResponse(Task t){
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getDueDate(),
                t.getTaskType().getTaskType(),
                t.getStatus()
        );
    }
}
