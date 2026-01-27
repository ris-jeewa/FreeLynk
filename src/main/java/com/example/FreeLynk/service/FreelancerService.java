package com.example.FreeLynk.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.Freelancer;
import com.example.FreeLynk.repository.FreelancerRepository;

@Service
public class FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

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

    // Create a new freelancer
    public Freelancer createFreelancer(Freelancer freelancer) {
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
