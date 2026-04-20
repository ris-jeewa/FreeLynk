package com.example.FreeLynk.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.dto.FreelancerCreateRequest;
import com.example.FreeLynk.model.Freelancer;
import com.example.FreeLynk.service.FreelancerService;

@RestController
@RequestMapping("/api/freelancers")
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    // GET ALL FREELANCERS
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Freelancer>> getAllFreelancers() {
        List<Freelancer> freelancers = freelancerService.getAllFreelancers();
        return ResponseEntity.ok(freelancers);
    }

    // GET FREELANCER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Freelancer> getFreelancerById(@PathVariable UUID id) {
        return freelancerService.getFreelancerById(id)
                .map(freelancer -> ResponseEntity.ok(freelancer))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET FREELANCER BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Freelancer> getFreelancerByUserId(@PathVariable UUID userId) {
        return freelancerService.getFreelancerByUserId(userId)
                .map(freelancer -> ResponseEntity.ok(freelancer))
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE FREELANCER
    @PostMapping("")
    public ResponseEntity<Freelancer> createFreelancer(@RequestBody FreelancerCreateRequest request) {
        Freelancer createdFreelancer = freelancerService.createFreelancerFromRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFreelancer);
    }

    // UPDATE FREELANCER
    @PutMapping("/{id}")
    public ResponseEntity<Freelancer> updateFreelancer(@PathVariable UUID id,
            @RequestBody Freelancer freelancerDetails) {
        try {
            Freelancer updatedFreelancer = freelancerService.updateFreelancer(id, freelancerDetails);
            return ResponseEntity.ok(updatedFreelancer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE FREELANCER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable UUID id) {
        try {
            freelancerService.deleteFreelancer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
