package com.example.FreeLynk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FreeLynk.model.JobApplication;

public interface JobAppRepository extends JpaRepository<JobApplication,Long> {
    
}
