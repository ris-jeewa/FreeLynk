package com.example.FreeLynk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.Job;
import com.example.FreeLynk.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(UUID id) {
        Job jobWithId = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job Not found for the provided id"));

        return jobWithId;
    }

    public Job updateJob(UUID id, Job job) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find a job with the id"));

        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setBudget(job.getBudget());
        existingJob.setStatus(job.getStatus());
        existingJob.setUpdatedAt(LocalDateTime.now());

        return jobRepository.save(existingJob);
    }

    public ResponseEntity<String> deleteJob(UUID id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return ResponseEntity.ok("Deleted the Job successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No job with this id");
        }

    }

    
}
