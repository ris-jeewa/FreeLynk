package com.example.FreeLynk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.exception.ResourceAlreadyExistException;
import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.Job;
import com.example.FreeLynk.repository.JobRepository;

@Service
public class JobService {
    
    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job){
        if (jobRepository.findById(job.getId()).isPresent()){
            throw new ResourceAlreadyExistException("Job alreafy exists with the same id");
        }

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(Long id){
       Job jobWithId = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job Not found"));

       return jobWithId;
    }
}
