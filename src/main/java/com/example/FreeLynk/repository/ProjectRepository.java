package com.example.FreeLynk.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FreeLynk.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    // Custom query methods can be defined here if needed
  

    
}
