package com.example.FreeLynk.model;

import java.util.List;
import java.util.Vector;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Freelancer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column()
    private String title;

    @Column()
    private String location;

    @Column()
    private double rating;
    
    @Column()
    private long numberOfReviews;

    @Column()
    private String githubUrl;

    @Column()
    private String linkedInUrl;

    @Column()
    private String portfolioUrl;

    @Column()
    private String bio;


    @Column()
    private String phone;


    @Column()
    private List<String> skills;
}
