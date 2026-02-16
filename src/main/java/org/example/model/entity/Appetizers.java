package org.example.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Appetizers {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String title;
    private String image;
    private double price;
    private String category;
}
