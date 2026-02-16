package org.example.model.entity;

import jakarta.persistence.*;
import org.example.enums.OrderStatus;
import org.example.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String invoiceId;

        private LocalDateTime orderDate;

        private Double subTotal;

        private Double discountAmount;

        private Double taxAmount;

        private Double totalAmount;

        @Enumerated(EnumType.STRING)
        private OrderStatus status;  // PENDING, PAID, CANCELLED

        @Enumerated(EnumType.STRING)
        private PaymentMethod paymentMethod;  // CASH, CARD, QR

        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderItems> orderItems;



}
