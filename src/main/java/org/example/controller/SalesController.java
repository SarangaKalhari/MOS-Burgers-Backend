package org.example.controller;

import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.service.Salesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {


    @Autowired
    public Salesservice salesservice ;


    @GetMapping("/daily")
    public List<Order> dailyOrders() {
        return salesservice.getDailyOrders();
    }

}
