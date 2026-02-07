package com.example.FreeLynk.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.Freelancer;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, UUID> {
    Optional<Freelancer> findByUserId(UUID userId);
} 