package com.example.FreeLynk.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.Project;
import com.example.FreeLynk.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by ID
    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.findById(id);
    }

    // Update project
    public Project updateProject(UUID id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setJobId(updatedProject.getJobId());
            project.setFreelancerId(updatedProject.getFreelancerId());
            project.setClientId(updatedProject.getClientId());
            project.setStatus(updatedProject.getStatus());
            project.setUpdatedAt(updatedProject.getUpdatedAt());
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    // Delete project
    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }
}

    
