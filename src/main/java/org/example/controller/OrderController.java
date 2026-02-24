package org.example.controller;

import org.example.model.dto.OrderRequest;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
