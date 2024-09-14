package com.mycompany.controller;

import com.mycompany.model.DeliveryOrder;
import com.mycompany.model.DeliveryOrderForm;
import com.mycompany.service.DeliveryOrderService;
import com.mycompany.service.FoodService;
import com.mycompany.service.PaymentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/deliveryOrder")
    public String showAddForm(Model model) {
        model.addAttribute("deliveryOrderForm", new DeliveryOrderForm()); // initialize form backing object
        model.addAttribute("foodItems", foodService.getAllFood());
        model.addAttribute("paymentMethods", paymentService.getAllPaymentMethods());
        return "deliveryOrder";
    }

    @PostMapping("/deliveryOrder/save")
    public String saveDeliveryOrder(@ModelAttribute("deliveryOrderForm") DeliveryOrderForm form, Model model) {
        try {
            System.out.println("Received form: " + form);
            Map<Integer, Integer> foodIdToQuantity = new HashMap<>();
            for (Integer foodId : form.getFoodIds()) {
                foodIdToQuantity.put(foodId, 1); // Assuming a quantity of 1 for simplicity
            }

            DeliveryOrder order = deliveryOrderService.createDeliveryOrder(foodIdToQuantity, form.getPaymentId(), form.getDeliveryAddress(), form.getPhoneNumber());
            model.addAttribute("successMessage", "Order Placed Successfully");
            model.addAttribute("deliveryOrder", new DeliveryOrder()); // to reset the form
            model.addAttribute("foodItems", foodService.getAllFood());
            model.addAttribute("paymentMethods", paymentService.getAllPaymentMethods());
            return "deliveryOrder";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to process order: " + e.getMessage());
            model.addAttribute("foodItems", foodService.getAllFood());
            model.addAttribute("paymentMethods", paymentService.getAllPaymentMethods());
            return "deliveryOrder";
        }
    }


    @GetMapping("/deliveryOrderHistory")
    public String showHistory(Model model) {
        model.addAttribute("deliveryOrders", deliveryOrderService.getAllDeliveryOrders());
        return "deliveryOrderHistory";
    }

    @GetMapping("/deleteDeliveryOrder/{id}")
    public String deleteDeliveryOrder(@PathVariable Integer id) {
        deliveryOrderService.deleteDeliveryOrder(id);
        return "redirect:/deliveryOrderHistory";
    }
}
