package com.mycompany.controller;

import com.mycompany.model.Payment;
import com.mycompany.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public String showPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());  // Simplified instantiation directly in the attribute
        return "payment";
    }

    @PostMapping("/payment/save")
    public String savePayment(@ModelAttribute Payment payment) {
        paymentService.save(payment);
        return "redirect:/adminHome";
    }

}
