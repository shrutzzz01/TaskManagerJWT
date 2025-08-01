package com.taskmanager.service;

import com.taskmanager.dto.*;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.mapper.TaskMapper;
import com.taskmanager.mapper.TaskTypeMapper;
import com.taskmanager.model.*;
import com.taskmanager.repository.*;
import jakarta.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskMapper taskMapper;
    public TaskService(UserRepository userRepository, TaskRepository taskRepository, TaskTypeRepository taskTypeRepository, TaskMapper taskMapper){
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
        this.taskTypeRepository=taskTypeRepository;
        this.taskMapper=taskMapper;
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        TaskType taskType = taskTypeRepository
                .findByTaskTypeAndCreatedBy(taskRequest.taskType().taskType(), user)
                .orElseGet(() -> {
                    TaskType newType = new TaskType();
                    newType.setTaskType(taskRequest.taskType().taskType());
                    newType.setCreatedBy(user);
                    return taskTypeRepository.save(newType);
                });
        Task task = taskMapper.toEntity(taskRequest, user, taskType);
        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
    }

    public TaskResponse getByTitle(String title){
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Task task=taskRepository.findByTitleAndUser(title, user);
        if(task==null)
                throw new ResourceNotFoundException("task with this title not found");
        return taskMapper.toResponse(task);
    }
    public List<TaskResponse> getByUser(){
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User not found"));
        return taskRepository.findByUser(user).stream().map(taskMapper::toResponse).toList();
    }
    public List<TaskResponse> getByStatus(String status){
        String currUserName= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(currUserName).orElseThrow(()->new ResourceNotFoundException("User not found"));
        TaskStatus taskStatus;
        try{
            taskStatus=TaskStatus.valueOf(status.toUpperCase());
        } catch(IllegalArgumentException e){
            throw new ResourceNotFoundException("Status not found");
        }
        List<Task> tasks = taskRepository.findByStatusAndUser(taskStatus, user);
        return tasks.stream().map(taskMapper::toResponse).toList();
    }
    public List<TaskResponse> getByTaskType(String taskType){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
        TaskType type = taskTypeRepository.findByTaskTypeAndCreatedBy(taskType, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task type not found"));
        List<Task> tasks= taskRepository.findByTaskTypeAndUser(type, user);
        return tasks.stream().map(taskMapper::toResponse).toList();
    }
    @Transactional
    public TaskResponse updateTask(String title, TaskRequest taskRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findByTitleAndUser(title, user);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with title: " + title);
        }
        TaskType taskType = taskTypeRepository
                .findByTaskTypeAndCreatedBy(taskRequest.taskType().taskType(), user)
                .orElseGet(() -> {
                    TaskType newType = new TaskType();
                    newType.setTaskType(taskRequest.taskType().taskType());
                    newType.setCreatedBy(user);
                    return taskTypeRepository.save(newType);
                });
        task.setDescription(taskRequest.description());
        task.setDueDate(taskRequest.dueDate());
        task.setTaskType(taskType);
        task.setStatus(taskRequest.status());
        taskRepository.save(task);
        return taskMapper.toResponse(task);
    }

    @Transactional
    public void updateStatus(String title, String status){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
        TaskStatus taskStatus;
        try{
            taskStatus=TaskStatus.valueOf(status.toUpperCase());
        }catch(IllegalArgumentException e){
            throw new ResourceNotFoundException("Status invalid");
        }
        Task task=taskRepository.findByTitleAndUser(title, user);
        if(task==null){
            throw new ResourceNotFoundException("No task found");
        }
        task.setStatus(taskStatus);
    }
    @Transactional
    public String deleteTask(String title){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Task task=taskRepository.findByTitleAndUser(title, user);
        if(task==null){
            return "No task found to delete";
        }
        Long id=task.getId();
        taskRepository.deleteById(id);
        return "Deleted successfully";
    }
}
