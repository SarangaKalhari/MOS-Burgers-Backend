package org.example.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppetizersDTO {

    private String code;
    private String title;
    private String image;
    private double price;
    private String category;
    private int stock;

}
