package org.example.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "desserts")
public class Dessert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String title;
    private String image;
    private double price;
    private String category;

}
