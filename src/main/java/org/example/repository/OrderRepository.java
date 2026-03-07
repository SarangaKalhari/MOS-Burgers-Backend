package org.example.repository;

import org.example.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
SELECT SUM(o.totalAmount)
FROM Order o
WHERE o.orderDate BETWEEN :start AND :end
""")
    Double getRevenueBetween(LocalDateTime start, LocalDateTime end);
}
