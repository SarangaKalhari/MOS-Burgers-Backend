package org.example.model.dto;

import lombok.*;
import org.example.enums.ItemCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemRequest {

    private String itemCode;
    private ItemCategory category;
    private double unitPrice;
    private Integer quantity;
    private double totalPrice;

}
