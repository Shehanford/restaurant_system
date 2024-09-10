package com.mycompany.service;

import com.mycompany.model.Food;
import com.mycompany.model.Locations;
import com.mycompany.model.Payment;
import com.mycompany.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentrepo;

    public void save(Payment payment) {
        paymentrepo.save(payment);
    }
    public List<Payment> getAllPaymentMethods(){
        return paymentrepo.findAll();
    }

}
