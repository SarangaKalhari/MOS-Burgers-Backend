package org.example.service;

import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderIemService {

    @Autowired
    private OrderItemRepository orderItemRepository ;


    public List<OrderItem> getDailyTopSelling() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        return orderItemRepository.findTopSellingItems(start, end, PageRequest.of(0,10));

    }
}
