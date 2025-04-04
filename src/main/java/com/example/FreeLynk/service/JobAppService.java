package com.example.FreeLynk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.Job;
import com.example.FreeLynk.model.JobApplication;
import com.example.FreeLynk.repository.JobAppRepository;
import com.example.FreeLynk.repository.JobRepository;
import com.example.FreeLynk.repository.UserRepository;

@Service
public class JobAppService {
    
    @Autowired
    private JobAppRepository jobAppRepository;
    private UserRepository userRepository;
    private JobRepository jobRepository;

//     public JobApplication createJobApplication(JobApplication jobAppDetails){
//         // if (!jobRepository.existsById(jobAppDetails.getJobId())){
//         //     throw new ResourceNotFoundException("Job not Found for the provided id");
//         // }
//         // if (!userRepository.existsById(jobAppDetails.getFreelancerId())){
//         //     throw new ResourceNotFoundException("User not Found for the provided id");
//         // }
// System.out.println(jobAppDetails);
//         return jobAppRepository.save(jobAppDetails);
//     }


public JobApplication createJobApplication(JobApplication job) {
    return jobAppRepository.save(job);
}

    public List<JobApplication> getAllJobApplications(){
        return jobAppRepository.findAll();
    }

    public JobApplication getJobApplication(Long id){
        JobApplication jobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        return jobApp;
    }

    public JobApplication updateJobApplication(Long id, JobApplication jobAppDetails){
        JobApplication existingJobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        existingJobApp.setProposal(jobAppDetails.getProposal());
        existingJobApp.setBidAmount(jobAppDetails.getBidAmount());
        existingJobApp.setStatus(jobAppDetails.getStatus());
        existingJobApp.setAppliedAt(LocalDateTime.now());

        return jobAppRepository.save(existingJobApp);
    }

    public void deleteJobApplication(Long id){
        JobApplication existingJobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        jobAppRepository.delete(existingJobApp);
    }
}
