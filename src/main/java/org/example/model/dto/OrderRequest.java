package org.example.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private List<OrderItemRequest> items;
    private Double discount;
    private String paymentMethod;
}
