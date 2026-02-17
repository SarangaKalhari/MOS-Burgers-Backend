package org.example.controller;

import org.example.model.dto.OrderRequest;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(
            @RequestBody OrderRequest request
    ) {

        String invoiceId = orderService.placeOrder(request);

        return ResponseEntity.ok(
                "Order placed successfully. Invoice: " + invoiceId
        );
    }

}
