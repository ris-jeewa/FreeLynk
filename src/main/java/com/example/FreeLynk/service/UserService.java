package com.example.FreeLynk.service;

import java.util.List;
import java.util.Optional;

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

    public UserProfileResponse getUserProfile(Long userId) {
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
