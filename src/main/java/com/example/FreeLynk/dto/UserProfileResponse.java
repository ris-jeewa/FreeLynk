package com.example.FreeLynk.dto;
import java.util.UUID;

import com.example.FreeLynk.model.User;
import com.example.FreeLynk.model.Freelancer;
import com.example.FreeLynk.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private String profilePictureUrl;
    private String bio;
    private String phoneNumber;
    private Freelancer freelancerProfile;

    // Constructor for regular user
    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.bio = user.getBio();
        this.phoneNumber = user.getPhoneNumber();
        this.freelancerProfile = null;
    }

    // Constructor for user with freelancer profile
    public UserProfileResponse(User user, Freelancer freelancer) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.bio = user.getBio();
        this.phoneNumber = user.getPhoneNumber();
        
        if (freelancer != null) {
            this.freelancerProfile = freelancer;
        } else {
            this.freelancerProfile = null;
        }
    }

} 