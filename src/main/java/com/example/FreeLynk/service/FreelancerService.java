package com.example.FreeLynk.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.dto.FreelancerCreateRequest;
import com.example.FreeLynk.model.Freelancer;
import com.example.FreeLynk.model.User;
import com.example.FreeLynk.repository.FreelancerRepository;
import com.example.FreeLynk.repository.UserRepository;

@Service
public class FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all freelancers
    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    // Get freelancer by ID
    public Optional<Freelancer> getFreelancerById(UUID id) {
        return freelancerRepository.findById(id);
    }

    // Get freelancer by User ID
    public Optional<Freelancer> getFreelancerByUserId(UUID userId) {
        return freelancerRepository.findByUserId(userId);
    }

    // Create a new freelancer from flat request payload
    public Freelancer createFreelancerFromRequest(FreelancerCreateRequest request) {
        System.out.println(">>> FreelancerCreateRequest id=" + request.getId() + " name=" + request.getName());
        UUID userId = request.getId();
        if (userId == null) throw new RuntimeException("User id is required");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (request.getName() != null) {
            user.setName(request.getName());
            user = userRepository.save(user);
        }

        Freelancer freelancer = new Freelancer();
        freelancer.setUser(user);
        freelancer.setTitle(request.getTitle());
        freelancer.setLocation(request.getLocation());
        freelancer.setTimezone(request.getTimezone());
        freelancer.setExperienceLevel(request.getExperienceLevel());
        freelancer.setHourlyRate(request.getHourlyRate());
        freelancer.setAvailability(request.getAvailability());
        freelancer.setGithubUrl(request.getGithubUrl());
        freelancer.setLinkedinUrl(request.getLinkedinUrl());
        freelancer.setPortfolioUrl(request.getPortfolioUrl());
        freelancer.setSkills(request.getSkills());

        return freelancerRepository.save(freelancer);
    }

    // Create a new freelancer
    public Freelancer createFreelancer(Freelancer freelancer) {
        // Handle user creation or fetching
        if (freelancer.getUser() != null) {
            User providedUser = freelancer.getUser();
            User user;
            if (providedUser.getId() != null) {
                user = userRepository.findById(providedUser.getId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + providedUser.getId()));
            } else if (providedUser.getAuth0Id() != null) {
                user = userRepository.findByAuth0Id(providedUser.getAuth0Id())
                        .orElseGet(() -> userRepository.save(providedUser));
            } else {
                // If no ID or auth0Id, save the provided user as new
                user = userRepository.save(providedUser);
            }
            freelancer.setUser(user);
        }
        return freelancerRepository.save(freelancer);
    }

    // Update an existing freelancer
    public Freelancer updateFreelancer(UUID id, Freelancer freelancerDetails) {
        Freelancer existingFreelancer = freelancerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Freelancer not found with id: " + id));

        // Update fields
        existingFreelancer.setTitle(freelancerDetails.getTitle());
        existingFreelancer.setLocation(freelancerDetails.getLocation());
        existingFreelancer.setTimezone(freelancerDetails.getTimezone());
        existingFreelancer.setExperienceLevel(freelancerDetails.getExperienceLevel());
        existingFreelancer.setHourlyRate(freelancerDetails.getHourlyRate());
        existingFreelancer.setAvailability(freelancerDetails.getAvailability());
        existingFreelancer.setRating(freelancerDetails.getRating());
        existingFreelancer.setNumberOfReviews(freelancerDetails.getNumberOfReviews());
        existingFreelancer.setGithubUrl(freelancerDetails.getGithubUrl());
        existingFreelancer.setLinkedinUrl(freelancerDetails.getLinkedinUrl());
        existingFreelancer.setPortfolioUrl(freelancerDetails.getPortfolioUrl());
        existingFreelancer.setSkills(freelancerDetails.getSkills());
        existingFreelancer.setIsVerified(freelancerDetails.getIsVerified());

        return freelancerRepository.save(existingFreelancer);
    }

    // Delete a freelancer
    public void deleteFreelancer(UUID id) {
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Freelancer not found with id: " + id));
        freelancerRepository.delete(freelancer);
    }
}
