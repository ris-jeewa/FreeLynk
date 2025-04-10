package com.example.FreeLynk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query methods can be defined here if needed
  

    
}
