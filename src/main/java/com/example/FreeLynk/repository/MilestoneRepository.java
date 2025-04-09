package com.example.FreeLynk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FreeLynk.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByProjectId(Long projectId);
    
}
