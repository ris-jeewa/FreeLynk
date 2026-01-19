package com.example.FreeLynk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.JobApplication;


@Repository
public interface JobAppRepository extends JpaRepository<JobApplication, UUID> {
    
}
