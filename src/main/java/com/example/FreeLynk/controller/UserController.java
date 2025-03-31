package com.example.FreeLynk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.FreeLynk.dto.UserResponseDTO;
import com.example.FreeLynk.model.User;
import com.example.FreeLynk.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<User> createUserProfile(@RequestBody User user ) {
        // Add logic to create a new user profile
        User createdUser = userService.createUserProfile(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        // Add logic to fetch and return user profile
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    
}
