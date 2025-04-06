package com.example.FreeLynk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FreeLynk.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query methods can be defined here if needed
  

    
}
