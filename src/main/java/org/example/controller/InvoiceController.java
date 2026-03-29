package org.example.controller;

import org.example.model.entity.Order;
import org.example.repository.OrderRepository;
import org.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderRepository orderRepository ;

    @GetMapping("/invoice/{id}")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        byte[] pdf = invoiceService.generateInvoice(order);

        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=invoice-" + order.getInvoiceId() + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
