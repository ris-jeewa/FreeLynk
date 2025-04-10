package com.example.FreeLynk.model;

import java.time.LocalDateTime;

import com.example.FreeLynk.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long projectId;

    @Column
    private Long clientId;

    @Column
    private Long freelancerId;

    @Column
    private Double amount;

    @Column
    private PaymentStatus status;

    @Column
    private String stripePaymentId;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();


}
