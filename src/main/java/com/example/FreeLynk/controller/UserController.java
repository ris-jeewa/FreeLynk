package com.example.FreeLynk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.dto.UserProfileResponse;
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
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        // Add logic to fetch and return user profile with freelancer info if applicable
        UserProfileResponse userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
        // Add logic to update user profile
        User updatedUser = userService.updateUserProfile(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<User> updateUserProfileImage(@PathVariable Long userId, @RequestBody String imageUrl) {
        // Add logic to update user profile image
        User updatedUser = userService.updateUserProfileImage(userId, imageUrl);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/aboutme")
    public ResponseEntity<User> updateUserProfileAboutMe(@PathVariable Long userId, @RequestBody User user) {
        // Add logic to update user profile about me
        User updatedUser = userService.updateUserProfileAboutMe(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    // @PatchMapping("/{userId}")
    // public ResponseEntity<User> UpdateUserProfileSpecificFields(@PathVariable Long userId, @RequestBody User user){
    //     User updatedUser = userService.UpdateUserProfileSpecificFields(userId,user);
    //     return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);
    // }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
