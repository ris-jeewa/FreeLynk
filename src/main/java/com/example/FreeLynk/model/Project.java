package com.example.FreeLynk.model;

import java.time.LocalDateTime;

import com.example.FreeLynk.enums.ProjectStatus;

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
import lombok.ToString;

@Entity
@ToString
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long jobId;

    @Column()
    private Long freelancerId;

    @Column()
    private Long clientId;

    @Column() 
    private ProjectStatus status;

    @Column()
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column()
    private LocalDateTime updatedAt = LocalDateTime.now();


    
}
