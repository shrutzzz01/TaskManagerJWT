package com.taskmanager.controller;

import com.taskmanager.dto.UserRequest;
import com.taskmanager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRequest userRequest){
        String jwtToken=authService.register(userRequest);
        Map<String, String> response=new HashMap<>();
        response.put("token", jwtToken);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest){
        String usernameOrEmail=loginRequest.get("usernameOrEmail");
        String password= loginRequest.get("password");
        String jwtToken= authService.login(usernameOrEmail, password);
        Map<String, String> response=new HashMap<>();
        response.put("token", jwtToken);
        return ResponseEntity.ok(response);
    }
}
