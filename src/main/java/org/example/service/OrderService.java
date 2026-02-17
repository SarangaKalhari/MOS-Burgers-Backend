package org.example.service;

import jakarta.transaction.Transactional;
import org.example.enums.OrderStatus;
import org.example.enums.PaymentMethod;
import org.example.model.dto.OrderItemRequest;
import org.example.model.dto.OrderRequest;
import org.example.model.entity.Beverages;
import org.example.model.entity.Burger;
import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.repository.BeveragesRepository;
import org.example.repository.BurgerRepository;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private BeveragesRepository beveragesRepository;

    @Transactional
    public String placeOrder(OrderRequest request) {

        // 1. Save Order
        Order order = new Order();
        order.setInvoiceId(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);
        order.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

        orderRepository.save(order);

        // 2. Loop items
        for (OrderItemRequest item : request.getItems()) {

            // 3. Check category
            switch (item.getCategory()) {

                case "burger":
                    Burger burger = burgerRepository.findByCode(item.getItemCode())
                            .orElseThrow(() -> new RuntimeException("Burger not found"));

                    if (burger.getStock() < item.getQuantity()) {
                        throw new RuntimeException("Not enough stock");
                    }

                    burger.setStock(burger.getStock() - item.getQuantity());
                    burgerRepository.save(burger);
                    break;

                case "beverage":
                    Beverages beverage = beveragesRepository.findByCode(item.getItemCode())
                            .orElseThrow(() -> new RuntimeException("Beverage not found"));

                    if (beverage.getStock() < item.getQuantity()) {
                        throw new RuntimeException("Not enough stock");
                    }

                    beverage.setStock(beverage.getStock() - item.getQuantity());
                    beveragesRepository.save(beverage);
                    break;

                // appetizers & dessert same widiyata
            }

            // 4. Save order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItemCode(item.getItemCode());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getUnitPrice());
            orderItem.setTotalPrice(item.getTotalPrice());

            orderItemRepository.save(orderItem);
        }
        return null;
    }

    private String generateInvoiceId() {
        long count = orderRepository.count() + 1;
        return "INV-2026-" + String.format("%04d", count);
    }
}
