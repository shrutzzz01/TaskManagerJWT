package com.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="taskType")
public class TaskType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskTypeId;
    private String taskType;
    @ManyToOne
    @JoinColumn(name="userId")
    private User createdBy;
}
