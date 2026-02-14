package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BeveragesDTO {

    private String code;
    private String title;
    private String image;
    private double price;
    private String category;

}
