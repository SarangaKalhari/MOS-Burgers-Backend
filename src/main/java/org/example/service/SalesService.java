package org.example.service;

import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.example.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class SalesService {

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

    public List<Order> getWeeklyOrders() {

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        return salesRepository.findByOrderDateBetween(
                startOfWeek.atStartOfDay(),
                endOfWeek.atTime(LocalTime.MAX)
        );
    }

    public List<Order> getMonthlyOrders() {

        LocalDate today = LocalDate.now();

        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        return salesRepository.findByOrderDateBetween(
                startOfMonth.atStartOfDay(),
                endOfMonth.atTime(LocalTime.MAX)
        );
    }

    public List<Order> getTotalOrders() {
        return salesRepository.findAll();
    }
}
