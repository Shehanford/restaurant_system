package com.mycompany.controller;

import com.mycompany.model.Locations;
import com.mycompany.model.Payment;
import com.mycompany.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public String showPaymentpage(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payment";
    }

    @PostMapping("/payment/save")
    public String savePayment(@ModelAttribute Payment payment){
        paymentService.save(payment);
        return "redirect:/adminHome";
    }
}