package com.example.FreeLynk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.User;
import com.example.FreeLynk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserProfile(Long userId) {
        // Fetch user from the repository using userId
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user;
    }

    public User createUserProfile(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ResourceNotFoundException("No users found");
        }
        return users;
    }

    public User updateUserProfile(Long userId, User user) {
        // Fetch existing user from the repository
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Update the fields of the existing user with the new values
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setProfilePictureUrl(user.getProfilePictureUrl());
        
        // Save the updated user back to the repository
        return userRepository.save(existingUser);
    }

    public User updateUserProfileImage(Long userId, String imageUrl) {
        // Fetch existing user from the repository
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Update the profile picture URL
        existingUser.setProfilePictureUrl(imageUrl);
        
        // Save the updated user back to the repository
        return userRepository.save(existingUser);
    }


    public User updateUserProfileAboutMe(Long UserId,User user){
        User existingUser = userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (user.getEmail() != null)existingUser.setEmail(user.getEmail());
        if (user.getBio() != null)existingUser.setBio(user.getBio());
        if (user.getPhoneNumber() != null)existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
