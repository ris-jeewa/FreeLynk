package com.example.FreeLynk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    
}
