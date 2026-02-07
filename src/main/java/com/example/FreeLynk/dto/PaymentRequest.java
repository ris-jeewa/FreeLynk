package com.example.FreeLynk.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long amount;      // amount in cents
    private String currency;  // "usd", "lkr", etc.
}
