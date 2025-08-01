package com.taskmanager.service;

import com.taskmanager.dto.TaskTypeResponse;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.mapper.TaskTypeMapper;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskType;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.TaskTypeRepository;
import com.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskTypeService(TaskTypeRepository taskTypeRepository, TaskRepository taskRepository,UserRepository userRepository){
        this.taskTypeRepository=taskTypeRepository;
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
    }
    public List<TaskTypeResponse> getAll() {
        String currUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(currUsername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<TaskType> allTypes = taskTypeRepository.findByCreatedBy(user);
        if(allTypes.isEmpty()){
            throw new ResourceNotFoundException("No tasks found");
        }
        return allTypes.stream()
                .map(TaskTypeMapper::toResponse)
                .toList();
    }

    @Transactional
    public void updateTaskType(String type, String title){
        String currUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(currUsername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findByTitleAndUser(title, user);
        if(task == null){
            throw new ResourceNotFoundException("Task not found");
        }

        TaskType existingType = taskTypeRepository.findByTaskTypeAndCreatedBy(type, user)
                .orElseGet(() -> {
                    TaskType newType = new TaskType();
                    newType.setTaskType(type);
                    newType.setCreatedBy(user);
                    return taskTypeRepository.save(newType);
                });

        task.setTaskType(existingType);
    }

}
