package com.example.FreeLynk.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.dto.PaymentRequest;
import com.example.FreeLynk.dto.PaymentResponse;
import com.example.FreeLynk.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public PaymentResponse createPaymentIntent(@RequestBody PaymentRequest request) throws Exception {
        return paymentService.createPaymentIntent(request);
    }
    
}


