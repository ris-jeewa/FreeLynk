package com.example.FreeLynk.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FreelancerCreateRequest {

    @JsonProperty("id")
    private UUID id; // user primary key
    private String name;
    private String title;
    private String location;
    private String timezone;
    private String experienceLevel;
    private BigDecimal hourlyRate;
    private String availability;
    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;
    private Map<String, Object> skills;
}
