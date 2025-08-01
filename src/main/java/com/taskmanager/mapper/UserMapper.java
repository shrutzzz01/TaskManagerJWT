package com.taskmanager.mapper;

import com.taskmanager.dto.UserRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static User toEntity(UserRequest user){
        User u=new User();
        u.setUsername(user.username());
        u.setEmail(user.email());
        u.setPassword(user.password());
        return u;
    }
    public static UserResponse toResponse(User u){
        return new UserResponse(
                u.getUserId(),
                u.getUsername(),
                u.getEmail()
        );
    }
}
