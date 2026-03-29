package org.example.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private LocalDateTime order_date;
    private double subtotal;
    private BigDecimal discount;
    private BigDecimal total_amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;
}
