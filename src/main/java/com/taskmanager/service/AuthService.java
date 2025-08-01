package com.taskmanager.service;

import com.taskmanager.dto.UserRequest;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import com.taskmanager.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
    }
    public String register(UserRequest userRequest){
        if(userRepository.findByUsername(userRequest.username()).isPresent()){
            throw new RuntimeException("Username already taken");
        }
        if(userRepository.findByEmail(userRequest.email()).isPresent()){
            throw new RuntimeException("Email already registered");
        }
        User user= User.builder().username(userRequest.username()).email(userRequest.email()).password(passwordEncoder.encode(userRequest.password())).build();
        userRepository.save(user);
        return jwtUtil.generateToken(user.getUsername());
    }
    public String login(String usernameOrEmail, String password){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
            );
        } catch(Exception ex){
            throw new RuntimeException("Invalid username/email or password");
        }
        User user=userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()->new RuntimeException("User not found"));
        return jwtUtil.generateToken(user.getUsername());
    }
}
