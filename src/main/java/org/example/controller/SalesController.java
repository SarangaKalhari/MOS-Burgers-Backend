package org.example.controller;

import org.example.model.entity.Order;
import org.example.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {


    @Autowired
    public SalesService salesservice ;


    @GetMapping("/daily")
    public List<Order> dailyOrders() {
        return salesservice.getDailyOrders();
    }

    @GetMapping("/weekly")
    public List<Order> weeklyOrders() {
        return salesservice.getWeeklyOrders();
    }

    @GetMapping("/monthly")
    public List<Order> monthlyOrders() {
        return salesservice.getMonthlyOrders();
    }

}
