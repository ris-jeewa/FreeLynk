package com.example.FreeLynk.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.FreeLynk.enums.MilestoneStatus;

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
@Table(name = "milestones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID projectId;

    @Column
    private String description;

    @Column
    private Double amount;

    @Column
    private MilestoneStatus status;

    @Column
    private LocalDateTime dueDate = LocalDateTime.now();

}
