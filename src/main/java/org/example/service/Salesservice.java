package org.example.service;

import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.example.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class Salesservice {

    @Autowired
    public OrderItemRepository orderItemRepository ;

    @Autowired
    public SalesRepository salesRepository ;

    public List<OrderItem> getOrders() {
        return orderItemRepository.findAll();

    }

    public List<Order> getDailyOrders() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        return salesRepository.findByOrderDateBetween(start, end);
    }
}
