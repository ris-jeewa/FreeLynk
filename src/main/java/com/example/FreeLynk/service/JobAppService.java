package com.example.FreeLynk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.exception.ResourceNotFoundException;
import com.example.FreeLynk.model.JobApplication;
import com.example.FreeLynk.repository.JobAppRepository;

@Service
public class JobAppService {
    
    @Autowired
    private JobAppRepository jobAppRepository;

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

    public JobApplication getJobApplication(UUID id){
        JobApplication jobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        return jobApp;
    }

    public JobApplication updateJobApplication(UUID id, JobApplication jobAppDetails){
        JobApplication existingJobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        existingJobApp.setProposal(jobAppDetails.getProposal());
        existingJobApp.setBidAmount(jobAppDetails.getBidAmount());
        existingJobApp.setStatus(jobAppDetails.getStatus());
        existingJobApp.setAppliedAt(LocalDateTime.now());

        return jobAppRepository.save(existingJobApp);
    }

    public void deleteJobApplication(UUID id){
        JobApplication existingJobApp = jobAppRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Job Application Found"));

        jobAppRepository.delete(existingJobApp);
    }
}
