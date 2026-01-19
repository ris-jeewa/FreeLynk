package com.example.FreeLynk.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.dto.UserProfileResponse;
import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.User;
import com.example.FreeLynk.model.Freelancer;
import com.example.FreeLynk.repository.UserRepository;
import com.example.FreeLynk.repository.FreelancerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FreelancerRepository freelancerRepository;

    public UserProfileResponse getUserProfile(UUID userId) {
        logger.info("Fetching user profile for userId: {}", userId);
        
        // Fetch user from the repository using userId
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        logger.info("Found user: {} with role: {}", user.getName(), user.getRole());
        
        // Check if user is a freelancer and fetch freelancer profile
        if (user.getRole() != null && user.getRole().name().equals("FREELANCER")) {
            logger.info("User is a freelancer, fetching freelancer profile");
            Optional<Freelancer> freelancer = freelancerRepository.findByUserId(userId);
            
            if (freelancer.isPresent()) {
                logger.info("Found freelancer profile for user: {}", user.getName());
                return new UserProfileResponse(user, freelancer.get());
            } else {
                logger.warn("User is marked as freelancer but no freelancer profile found for userId: {}", userId);
                return new UserProfileResponse(user, null);
            }
        }
        // else if (user.getRole() != null && user.getRole().name().equals("CLIENT")) {
        //     logger.info("User is a client, fetching client profile");
        //     Optional<Client> client = clientRepository.findByUserId(userId);
        //     if (client.isPresent()) {
        //         logger.info("Found client profile for user: {}", user.getName());
        //         return new UserProfileResponse(user, client.get());
        //     } else {
        //         logger.warn("User is marked as client but no client profile found for userId: {}", userId);
        //         return new UserProfileResponse(user, null);
        //     }
        // }
        
        logger.info("User is not a freelancer, returning basic profile");
        // Return regular user profile without freelancer information
        return new UserProfileResponse(user);
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

    public User updateUserProfile(UUID userId, User user) {
        logger.info("Updating user profile for userId: {}", userId);
        
        // Fetch existing user from the repository
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        logger.info("Found existing user: {}", existingUser.getName());
        
        // Update the fields of the existing user with the new values, only if not null
        if (user.getName() != null) {
            existingUser.setName(user.getName());
            logger.info("Updated name to: {}", user.getName());
        }
        
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
            logger.info("Updated email to: {}", user.getEmail());
        }
        
        if (user.getProfilePictureUrl() != null) {
            existingUser.setProfilePictureUrl(user.getProfilePictureUrl());
            logger.info("Updated profile picture URL");
        }
        
        if (user.getBio() != null) {
            existingUser.setBio(user.getBio());
            logger.info("Updated bio");
        }
        
        if (user.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(user.getPhoneNumber());
            logger.info("Updated phone number");
        }
        
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
            logger.info("Updated role to: {}", user.getRole());
        }
        
        // Save the updated user back to the repository
        User savedUser = userRepository.save(existingUser);
        logger.info("Successfully saved updated user: {}", savedUser.getName());
        
        return savedUser;
    }

    public User updateUserProfileImage(UUID userId, String imageUrl) {
        // Fetch existing user from the repository
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        // Update the profile picture URL
        existingUser.setProfilePictureUrl(imageUrl);
        
        // Save the updated user back to the repository
        return userRepository.save(existingUser);
    }


    public User updateUserProfileAboutMe(UUID UserId,User user){
        User existingUser = userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (user.getEmail() != null)existingUser.setEmail(user.getEmail());
        if (user.getBio() != null)existingUser.setBio(user.getBio());
        if (user.getPhoneNumber() != null)existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

}
