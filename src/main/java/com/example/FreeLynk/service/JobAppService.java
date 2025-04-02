package com.example.FreeLynk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.JobApplication;
import com.example.FreeLynk.repository.JobAppRepository;

@Service
public class JobAppService {
    
    @Autowired
    private JobAppRepository jobAppRepository;

    public JobApplication createJobApplication(JobApplication jobAppDetails){
        return jobAppRepository.save(jobAppDetails);
    }
}
