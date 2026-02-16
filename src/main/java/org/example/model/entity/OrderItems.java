package org.example.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String category; // BURGER, BEVERAGE, DESSERT

    private Double unitPrice;

    private Integer quantity;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
