package com.mycompany.service;

import com.mycompany.model.DeliveryOrder;
import com.mycompany.model.Food;
import com.mycompany.model.OrderFood;
import com.mycompany.model.Payment;
import com.mycompany.repository.DeliveryOrderRepository;
import com.mycompany.repository.FoodRepository;
import com.mycompany.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DeliveryOrderService {
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MockPaymentService mockPaymentService;

    @Transactional
    public DeliveryOrder createDeliveryOrder(Map<Integer, Integer> foodIdToQuantity, Integer paymentId, String deliveryAddress, String phoneNumber) {
        DeliveryOrder order = new DeliveryOrder();
        order.setDeliveryAddress(deliveryAddress);
        order.setPhoneNumber(phoneNumber);

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment method not found"));
        order.setPayment(payment);

        foodIdToQuantity.forEach((foodId, quantity) -> {
            Food food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));
            OrderFood orderFood = new OrderFood(order, food, quantity);
            order.addOrderFood(orderFood);
        });

        order.calculateTotalAmount();

        String paymentResult = mockPaymentService.processPayment(order.getTotalAmount());
        if (!paymentResult.startsWith("SUCCESS:")) {
            throw new RuntimeException("Payment failed: " + paymentResult);
        }

        order.setTransactionId(paymentResult.split(":")[1]);
        return deliveryOrderRepository.save(order);
    }


    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    public DeliveryOrder getDeliveryOrderById(Integer id) {
        return deliveryOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Delivery Order not found"));
    }

    public void deleteDeliveryOrder(Integer id) {
        deliveryOrderRepository.deleteById(id);
    }
}
