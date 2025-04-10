package com.example.FreeLynk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FreeLynk.model.Payment;
import com.example.FreeLynk.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    // @GetMapping("/client/{clientId}")
    // public ResponseEntity<List<Payment>> getPaymentsByClientId(@PathVariable Long clientId) {
    //     return ResponseEntity.ok(paymentService.getPaymentsByClientId(clientId));
    // }

    // @GetMapping("/freelancer/{freelancerId}")
    // public ResponseEntity<List<Payment>> getPaymentsByFreelancerId(@PathVariable Long freelancerId) {
    //     return ResponseEntity.ok(paymentService.getPaymentsByFreelancerId(freelancerId));
    // }

    // @GetMapping("/project/{projectId}")
    // public ResponseEntity<List<Payment>> getPaymentsByProjectId(@PathVariable Long projectId) {
    //     return ResponseEntity.ok(paymentService.getPaymentsByProjectId(projectId));
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
    
}
