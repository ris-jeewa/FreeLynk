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

import com.example.FreeLynk.model.JobApplication;
import com.example.FreeLynk.service.JobAppService;

@RestController
@RequestMapping("/api/job-applications")
public class JobAppController {
    
    @Autowired
    private JobAppService jobAppService;

    @PostMapping("")
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobAppDetails){
        // System.out.println("********** "+ jobAppDetails+" ***********");
        JobApplication createdJobApplication = jobAppService.createJobApplication(jobAppDetails);

        return new ResponseEntity<>(createdJobApplication,HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<JobApplication>> getAllJobApplications(){
        List<JobApplication> existingJobApps = jobAppService.getAllJobApplications();

        return new ResponseEntity<>(existingJobApps,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getJobApplication(@PathVariable UUID id){
        JobApplication existingJob = jobAppService.getJobApplication(id);

        return ResponseEntity.ok(existingJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(@PathVariable UUID id, @RequestBody JobApplication jobAppDetails){
        JobApplication updatedJobApp = jobAppService.updateJobApplication(id, jobAppDetails);

        return ResponseEntity.ok(updatedJobApp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobApplication(@PathVariable UUID id){
        jobAppService.deleteJobApplication(id);

        return ResponseEntity.ok("Job Application Deleted Successfully");
    }


}
