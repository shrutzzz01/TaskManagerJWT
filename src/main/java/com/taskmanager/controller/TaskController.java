package com.taskmanager.controller;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.TaskTypeRequest;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }
    @PostMapping
    public TaskResponse create(@RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }
    @GetMapping("/{title}")
    public TaskResponse getByTitle(@PathVariable String title){
        return taskService.getByTitle(title);
    }
    @GetMapping
    public List<TaskResponse> getByUser(){
        return taskService.getByUser();
    }
    @GetMapping("/status")
    public List<TaskResponse> getByStatus(@RequestParam String status){
        return taskService.getByStatus(status);
    }
    @GetMapping("/taskType")
    public List<TaskResponse> getTaskByType(@RequestParam String taskType){
        return taskService.getByTaskType(taskType);
    }
    @PutMapping
    public TaskResponse updateTask(@RequestParam String title, @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(title, taskRequest);
    }
    @PatchMapping
    public ResponseEntity<String> updateStatus(@RequestParam String title, @RequestParam String status){
        taskService.updateStatus(title, status);
        return ResponseEntity.ok("Status updated successfully");
    }
    @DeleteMapping
    public String deleteTaskByTitle(@RequestParam String title){
        return taskService.deleteTask(title);
    }
}
