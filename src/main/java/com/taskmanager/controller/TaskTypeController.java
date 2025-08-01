package com.taskmanager.controller;

import com.taskmanager.dto.TaskTypeResponse;
import com.taskmanager.model.TaskType;
import com.taskmanager.service.TaskTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskTypes")
public class TaskTypeController {
    private final TaskTypeService taskTypeService;
    public TaskTypeController(TaskTypeService taskTypeService){
        this.taskTypeService=taskTypeService;
    }
    @GetMapping
    public List<TaskTypeResponse> getAllTypes(){
        return taskTypeService.getAll();
    }

    @PatchMapping
    public ResponseEntity<String> updateTaskTypeByTitle(@RequestParam String type,
                                                        @RequestParam String title) {
        taskTypeService.updateTaskType(type, title);
        return ResponseEntity.ok("TaskType updated successfully");
    }

}
