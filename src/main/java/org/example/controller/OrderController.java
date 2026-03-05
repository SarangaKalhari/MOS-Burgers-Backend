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

}
