package com.mycompany.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MockPaymentService {

    public String processPayment(BigDecimal amount) {
        // Simulate payment processing delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Generate a mock transaction ID
        String transactionId = UUID.randomUUID().toString();

        // Simulate a successful payment (you could add logic to sometimes return a failure)
        return "SUCCESS:" + transactionId;
    }
}
