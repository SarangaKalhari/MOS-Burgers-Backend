package org.example.repository;

import org.example.model.dto.TopSellingItemDTO;
import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    @Query("""
SELECT new org.example.model.dto.TopSellingItemDTO(
    oi.itemCode,
    SUM(oi.quantity)
)
FROM OrderItem oi
WHERE oi.order.orderDate BETWEEN :start AND :end
GROUP BY oi.itemCode
ORDER BY SUM(oi.quantity) DESC
""")
    List<TopSellingItemDTO> findTopSellingItems(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );
}
