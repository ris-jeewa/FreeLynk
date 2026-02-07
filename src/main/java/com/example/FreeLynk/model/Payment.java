package com.example.FreeLynk.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String paymentIntentId;
    private Long amount;
    private String currency;
    private String status; 

}
