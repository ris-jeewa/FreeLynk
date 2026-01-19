package com.example.FreeLynk.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.Milestone;
import com.example.FreeLynk.repository.MilestoneRepository;

@Service
public class MilestoneService {
    @Autowired
    private MilestoneRepository milestoneRepository;

    public Milestone createMilestone(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    public Milestone getMilestoneById(UUID id) {
        return milestoneRepository.findById(id).orElse(null);
    }

    public List<Milestone> getMilestonesByProjectId(UUID projectId) {
        return milestoneRepository.findByProjectId(projectId);
    }

    public Milestone updateMilestone(UUID id, Milestone updatedMilestone) {
        Milestone existing = getMilestoneById(id);
        if (existing != null) {
            existing.setAmount(updatedMilestone.getAmount());
            existing.setDescription(updatedMilestone.getDescription());
            existing.setDueDate(updatedMilestone.getDueDate());
            existing.setStatus(updatedMilestone.getStatus());
            return milestoneRepository.save(existing);
        }
        return null;
    }

    public void deleteMilestone(UUID id) {
        milestoneRepository.deleteById(id);
    }
}
