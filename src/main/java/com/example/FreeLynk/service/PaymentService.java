package com.example.FreeLynk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FreeLynk.model.Payment;
import com.example.FreeLynk.repository.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // public List<Payment> getPaymentsByClientId(Long clientId) {
    //     return paymentRepository.findByClientId(clientId);
    // }

    // public List<Payment> getPaymentsByFreelancerId(Long freelancerId) {
    //     return paymentRepository.findByFreelancerId(freelancerId);
    // }

    // public List<Payment> getPaymentsByProjectId(Long projectId) {
    //     return paymentRepository.findByProjectId(projectId);
    // }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
