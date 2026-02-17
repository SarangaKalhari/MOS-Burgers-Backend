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

        Order order = new Order();
        order.setInvoiceId(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);
        order.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

        order = orderRepository.save(order); // save first

        double subTotal = 0;

        for (OrderItemRequest item : request.getItems()) {

            double itemTotal = item.getUnitPrice() * item.getQuantity();
            subTotal += itemTotal;

            switch (item.getCategory().toLowerCase()) {

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
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItemCode(item.getItemCode());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getUnitPrice());
            orderItem.setTotalPrice(itemTotal);

            orderItemRepository.save(orderItem);
        }

        double tax = subTotal * 0.1;
        double discount = 0;
        double total = subTotal + tax - discount;

        order.setSubTotal(subTotal);
        order.setTaxAmount(tax);
        order.setDiscountAmount(discount);
        order.setTotalAmount(total);

        orderRepository.save(order);

        return order.getInvoiceId();
    }

    private String generateInvoiceId() {
        long count = orderRepository.count() + 1;
        return "INV-2026-" + String.format("%04d", count);
    }
}
