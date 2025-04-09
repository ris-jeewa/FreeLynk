package com.example.FreeLynk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.model.Milestone;
import com.example.FreeLynk.service.MilestoneService;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

     @Autowired
    private MilestoneService milestoneService;

    @PostMapping
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone) {
        return ResponseEntity.ok(milestoneService.createMilestone(milestone));
    }

    @GetMapping
    public ResponseEntity<List<Milestone>> getAllMilestones() {
        return ResponseEntity.ok(milestoneService.getAllMilestones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable Long id) {
        Milestone milestone = milestoneService.getMilestoneById(id);
        return milestone != null ? ResponseEntity.ok(milestone) : ResponseEntity.notFound().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Milestone>> getByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(milestoneService.getMilestonesByProjectId(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable Long id, @RequestBody Milestone milestone) {
        Milestone updated = milestoneService.updateMilestone(id, milestone);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
    
}
