package com.example.FreeLynk.controller;

import com.example.FreeLynk.service.PaymentService;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class WebhookController {

    private final PaymentService paymentService;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @PostMapping("/webhook")
    public String handleWebhook(@RequestHeader("Stripe-Signature") String signature,
                                @RequestBody String payload) {

        try {
            Event event = Webhook.constructEvent(payload, signature, webhookSecret);

            switch (event.getType()) {

                case "payment_intent.succeeded":
                    PaymentIntent succeededIntent = (PaymentIntent) event.getDataObjectDeserializer()
                            .getObject().orElse(null);

                    paymentService.updatePaymentStatus(
                            succeededIntent.getId(),
                            "succeeded"
                    );
                    break;

                case "payment_intent.payment_failed":
                    PaymentIntent failedIntent = (PaymentIntent) event.getDataObjectDeserializer()
                            .getObject().orElse(null);

                    paymentService.updatePaymentStatus(
                            failedIntent.getId(),
                            "failed"
                    );
                    break;
            }

            return "OK";

        } catch (Exception e) {
            return "Invalid signature";
        }
    }
}
