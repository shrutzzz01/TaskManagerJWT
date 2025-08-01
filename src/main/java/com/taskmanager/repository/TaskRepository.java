package com.taskmanager.repository;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByStatusAndUser(TaskStatus status, User user);
    Task findByUserAndId(User user, Long id);
    List<Task> findByTaskTypeAndUser(TaskType type, User user);
    Task findByTitleAndUser(String title, User user);
}
