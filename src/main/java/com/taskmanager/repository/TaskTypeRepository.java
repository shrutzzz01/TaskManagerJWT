package com.taskmanager.repository;

import com.taskmanager.model.TaskType;
import com.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    Optional<TaskType> findByTaskTypeAndCreatedBy(String taskType, User createdBy);
    List<TaskType> findByCreatedBy(User user);

}
