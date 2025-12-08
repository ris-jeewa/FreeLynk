package com.example.FreeLynk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.FreeLynk.dto.PaymentRequest;
import com.example.FreeLynk.dto.PaymentResponse;
import com.example.FreeLynk.model.Payment;
import com.example.FreeLynk.repository.PaymentRepository;
import com.stripe.model.PaymentIntent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    /**
     * Create a Stripe PaymentIntent and save the record in the database.
     */
    public PaymentResponse createPaymentIntent(PaymentRequest request) throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("amount", request.getAmount());
        params.put("currency", request.getCurrency());
        params.put("automatic_payment_methods", Map.of("enabled", true));

        PaymentIntent intent = PaymentIntent.create(params);

        // Save payment in DB
        Payment payment = new Payment();
        payment.setPaymentIntentId(intent.getId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus("pending");
        paymentRepository.save(payment);

        return new PaymentResponse(intent.getClientSecret());
    }

    /**
     * Update payment status after receiving webhook from Stripe.
     */
    public void updatePaymentStatus(String paymentIntentId, String status) {

        paymentRepository.findByPaymentIntentId(paymentIntentId)
                .ifPresent(payment -> {
                    payment.setStatus(status);
                    paymentRepository.save(payment);
                });
    }
    
}
