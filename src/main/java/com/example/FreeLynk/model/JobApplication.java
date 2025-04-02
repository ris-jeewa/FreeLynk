package com.example.FreeLynk.model;

import java.time.LocalDateTime;

import com.example.FreeLynk.enums.JobAppStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="job_applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long jobId;

    @Column()
    private Long freelancerId;

    @Column()
    private String proposal;

    @Column()
    private Double bidAmount;

    @Column()
    private JobAppStatus status = JobAppStatus.PENDING;

    @Column()
    private LocalDateTime appliedAt = LocalDateTime.now();

}

