package com.taskmanager.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="`user`")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, length=20, nullable = false)
    private String username;
    @Column(unique = true,length = 50, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks=new ArrayList<>();
    @Builder
    public User(Long userId, String username, String email, String password, List<Task> tasks) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

}
