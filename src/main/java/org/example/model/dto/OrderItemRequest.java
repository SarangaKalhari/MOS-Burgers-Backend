package org.example.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemRequest {

    private String itemCode;
    private String category;
    private Double unitPrice;
    private Integer quantity;

}
