package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemCode;

    private String category; // BURGER, BEVERAGE, DESSERT

    private Double unitPrice;

    private Integer quantity;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
