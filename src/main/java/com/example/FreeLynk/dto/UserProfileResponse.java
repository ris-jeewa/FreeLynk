package com.example.FreeLynk.dto;

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
    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private String profilePictureUrl;
    private String bio;
    private String phoneNumber;
    private FreelancerProfile freelancerProfile;

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
            this.freelancerProfile = new FreelancerProfile(freelancer);
        } else {
            this.freelancerProfile = null;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FreelancerProfile {
        private Long id;
        private String title;
        private String location;
        private Double rating;
        private Long numberOfReviews;
        private String githubUrl;
        private String linkedinUrl;
        private String portfolioUrl;
        private Object skills; // JSON object for skills

        public FreelancerProfile(Freelancer freelancer) {
            this.id = freelancer.getId();
            this.title = freelancer.getTitle();
            this.location = freelancer.getLocation();
            this.rating = freelancer.getRating();
            this.numberOfReviews = freelancer.getNumberOfReviews();
            this.githubUrl = freelancer.getGithubUrl();
            this.linkedinUrl = freelancer.getLinkedinUrl();
            this.portfolioUrl = freelancer.getPortfolioUrl();
            this.skills = freelancer.getSkills();
        }
    }
} 