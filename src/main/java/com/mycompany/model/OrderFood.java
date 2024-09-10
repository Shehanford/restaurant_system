package com.mycompany.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_food")
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DeliveryOrder order;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private Integer quantity;

    public OrderFood() {}

    public OrderFood(DeliveryOrder order, Food food, Integer quantity) {
        this.order = order;
        this.food = food;
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return food.getPrice().multiply(new BigDecimal(quantity));
    }

    // Getters and setters
    public Integer getId() { return id;
    }
    public void setId(Integer id) { this.id = id; }
    public DeliveryOrder getOrder() { return order; }
    public void setOrder(DeliveryOrder order) { this.order = order; }
    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
