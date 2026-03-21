package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

@Entity
@Table(name = "dessert")
public class Desserts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String title;
    private String image;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private String category;
    private int stock;
}