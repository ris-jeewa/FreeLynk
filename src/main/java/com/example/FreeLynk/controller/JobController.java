package com.example.FreeLynk.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.model.Job;
import com.example.FreeLynk.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    @Autowired
    private JobService jobService;

    @PostMapping("")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job createdJob = jobService.createJob(job);

        return new ResponseEntity<>(createdJob,HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> allExistingJobs = jobService.getAllJobs();

        return new ResponseEntity<>(allExistingJobs,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getjobById(@PathVariable UUID id){
        Job existingJob = jobService.getJobById(id);

        return new ResponseEntity<>(existingJob,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable UUID id,@RequestBody Job job){
        Job updatedJob = jobService.updateJob(id,job);

        return new ResponseEntity<>(updatedJob,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable UUID id){
        return jobService.deleteJob(id);
    }
}
