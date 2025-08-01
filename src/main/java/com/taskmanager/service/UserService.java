package com.taskmanager.service;

import com.taskmanager.dto.UserRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
            this.userRepository=userRepository;
            this.passwordEncoder=passwordEncoder;
        }

        public User getUserByUsername(String username){
            return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User with this username not found"));
        }
        public User getUserByEmail(String email){
            return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User with this email not found"));
        }
        @Transactional
        public UserResponse updateUser(UserRequest request){
            String currUsername= SecurityContextHolder.getContext().getAuthentication().getName();
            User u=userRepository.findByUsername(currUsername).orElseThrow(()->new ResourceNotFoundException("User not found"));
            u.setUsername(request.username());
            u.setEmail(request.email());
            u.setPassword(passwordEncoder.encode(request.password()));
            userRepository.save(u);
            return new UserResponse(u.getUserId(), u.getUsername(), u.getEmail());
        }
        @Transactional
        public String deleteUser(String password){
            String currUsername=SecurityContextHolder.getContext().getAuthentication().getName();
            User user=userRepository.findByUsername(currUsername).orElseThrow(()->new ResourceNotFoundException("User not found"));
            if(!passwordEncoder.matches(password, user.getPassword())){
                throw new BadCredentialsException("Invalid password. Can't delete user");
            }
            userRepository.delete(user);
            return "Deleted";
        }
}
