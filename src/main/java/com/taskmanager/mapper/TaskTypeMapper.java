package com.taskmanager.mapper;

import com.taskmanager.dto.TaskTypeRequest;
import com.taskmanager.dto.TaskTypeResponse;
import com.taskmanager.model.*;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeMapper {
    public static TaskType toEntity(TaskTypeRequest request, User user){
        TaskType t=new TaskType();
        t.setTaskType(request.taskType());
        t.setCreatedBy(user);
        return t;
    }
    public static TaskType toEntity(String type, User user){
        TaskType t=new TaskType();
        t.setTaskType(type);
        t.setCreatedBy(user);
        return t;
    }
    public static TaskTypeResponse toResponse(TaskType type){
        return new TaskTypeResponse(
                type.getTaskTypeId(),
                type.getTaskType(),
                type.getCreatedBy().getUsername()
        );
    }
}
