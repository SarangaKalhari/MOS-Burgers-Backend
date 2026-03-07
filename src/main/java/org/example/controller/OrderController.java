package org.example.controller;

import org.example.model.dto.OrderRequest;
import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin("http://localhost:4200/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {

        String invoiceId = orderService.placeOrder(request);

        return ResponseEntity.ok(
                "Order placed successfully. Invoice: " + invoiceId
        );
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/revenue/daily")
    public Double dailyRevenue(){
        return orderService.getDailyRevenue();
    }

    @GetMapping("/revenue/weekly")
    public Double weeklyRevenue(){
        return orderService.getWeeklyRevenue();
    }

    @GetMapping("/revenue/monthly")
    public Double monthlyRevenue(){
        return orderService.getMonthlyRevenue();
    }

    @GetMapping("/revenue/total")
    public Double totalRevenue(){
        return orderService.getTotalRevenue();
    }

    @GetMapping("/orders/total")
    public Long totalOrders() {
        return orderService.getTotalOrders();
    }

    @GetMapping("/orders/daily")
    public Long dailyOrders() {
        return orderService.getDailyOrders();
    }

}
