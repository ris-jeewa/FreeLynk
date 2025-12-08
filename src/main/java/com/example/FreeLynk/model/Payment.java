package com.example.FreeLynk.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;


    private String paymentIntentId;
    private Long amount;
    private String currency;
    private String status; 

}
