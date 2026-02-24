package org.example.model.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.model.entity.OrderItem;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private String paymentMethod;
    private Double discount;
    private List<OrderItemRequest> items;

}
