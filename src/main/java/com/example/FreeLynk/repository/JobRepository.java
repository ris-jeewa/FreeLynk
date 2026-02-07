package com.example.FreeLynk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    
}
