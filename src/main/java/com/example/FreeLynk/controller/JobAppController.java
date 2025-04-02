package com.example.FreeLynk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.model.JobApplication;
import com.example.FreeLynk.service.JobAppService;

@RestController
@RequestMapping("/api/jobapp")
public class JobAppController {
    
    @Autowired
    private JobAppService jobAppService;

    @PostMapping("")
    public ResponseEntity<JobApplication> createJobApplication(JobApplication jobAppDetails){
        JobApplication createdJobApplication = jobAppService.createJobApplication(jobAppDetails);

        return new ResponseEntity<>(createdJobApplication,HttpStatus.CREATED);
    }
}
