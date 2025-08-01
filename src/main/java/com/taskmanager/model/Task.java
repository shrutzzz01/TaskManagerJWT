package com.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20, nullable=false)
    private String title;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="taskTypeId")
    private TaskType taskType;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
