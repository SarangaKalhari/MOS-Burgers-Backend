package org.example.service;

import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Salesservice {

    @Autowired
    public OrderItemRepository orderItemRepository ;

    public List<OrderItem> getOrders() {
        return orderItemRepository.findAll();

    }
}
