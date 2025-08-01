package com.taskmanager.controller;

import com.taskmanager.dto.UserRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.mapper.UserMapper;
import com.taskmanager.model.User;
import com.taskmanager.service.UserService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
      private final UserService userService;
      public UserController(UserService userService){
          this.userService=userService;
      }

      @PutMapping
      public UserResponse updateUser(@RequestBody UserRequest userRequest){
          return userService.updateUser(userRequest);
      }
      @DeleteMapping
      public String deleteUser(@RequestParam String password){
          return userService.deleteUser(password);
      }
}
