package com.example.FreeLynk.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Double rating;
    
    @Column()
    private Long numberOfReviews;

    @Column()
    private String githubUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column()
    private String portfolioUrl;

    @Column(columnDefinition = "jsonb")
    private Object skills;
}
