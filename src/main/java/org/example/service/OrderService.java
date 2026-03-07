package org.example.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.transaction.Transactional;
import org.example.enums.OrderStatus;
import org.example.enums.PaymentMethod;
import org.example.model.dto.OrderDTO;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private BeveragesRepository beveragesRepository;

     Order order = new Order();
//     List<OrderItem> orderItems = new ArrayList<>();

//    @Transactional
//    public String placeOrder(OrderRequest request) {
//
//        List<OrderItem> orderItems = request.getItems()
//                .stream()
//                .map(dto -> {
//                    OrderItem item = new OrderItem();
//                    item.setOrder(order);
//                    item.setItemCode(dto.getItemCode());
//                    item.setQuantity(dto.getQuantity());
//                    item.setUnitPrice(dto.getUnitPrice());
//                    item.setTotalPrice(dto.getUnitPrice() * dto.getQuantity());
//                    return item;
//                })
//                .toList();
//
//        order.setOrderItems(orderItems);
//
//        order.setInvoiceId(generateInvoiceId());
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus(OrderStatus.PAID);
//        order.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));
//
//        request.getItems().forEach(dto -> {
//
//            OrderItem item = new OrderItem();
//            item.setItemCode(dto.getItemCode());
//            item.setQuantity(dto.getQuantity());
//            item.setUnitPrice(dto.getUnitPrice());
//            item.setTotalPrice(dto.getUnitPrice() * dto.getQuantity());
//
//            order.addOrderItem(item);
//        });
//
//        orderRepository.save(order);
//
//
//
//        // 🔥 SAVE ORDER FIRST
//        order = orderRepository.save(order);
//
//        double subTotal = 0;
//
////        List<OrderItem> orderItemList = modelMapper.map()
//
//        // 2️⃣ Process Items
//        for (OrderItemRequest item : request.getItems()) {
//
//            if (item.getQuantity() == null || item.getQuantity() <= 0) {
//                throw new RuntimeException("Invalid quantity for item: " + item.getItemCode());
//            }
//
//            double itemTotal = item.getUnitPrice() * item.getQuantity();
//            subTotal += itemTotal;
//
//            switch (item.getCategory()) {
//
//                case "burger":
//                    Burger burger = burgerRepository.findByCode(item.getItemCode())
//                            .orElseThrow(() -> new RuntimeException("Burger not found"));
//
//                    if (burger.getStock() < item.getQuantity()) {
//                        throw new RuntimeException("Not enough stock");
//                    }
//
//                    burger.setStock(burger.getStock() - item.getQuantity());
//                    burgerRepository.save(burger);
//                    break;
//
//                case "beverage":
//                    Beverages beverage = beveragesRepository.findByCode(item.getItemCode())
//                            .orElseThrow(() -> new RuntimeException("Beverage not found"));
//
//                    if (beverage.getStock() < item.getQuantity()) {
//                        throw new RuntimeException("Not enough stock");
//                    }
//
//                    beverage.setStock(beverage.getStock() - item.getQuantity());
//                    beveragesRepository.save(beverage);
//                    break;
//            }
//
//            // 3️⃣ Save Order Item
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setItemCode(item.getItemCode());
//            orderItem.setQuantity(item.getQuantity());
//            orderItem.setUnitPrice(item.getUnitPrice());
//            orderItem.setTotalPrice(itemTotal);
//
//            orderItemRepository.save(orderItem);
//        }
//
//        // 4️⃣ Calculate totals
//        double tax = subTotal * 0.1;
//        double discount = 0;
//        double total = subTotal + tax - discount;
//
//        order.setSubTotal(subTotal);
//        order.setTaxAmount(tax);
//        order.setDiscountAmount(discount);
//        order.setTotalAmount(total);
//
//        orderRepository.save(order);
//
//        return order.getInvoiceId();
//    }

    @Transactional
    public String placeOrder(OrderRequest request) {

        // 1️⃣ Create Order
        Order order = new Order();
        order.setInvoiceId(generateInvoiceId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);
        order.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

        double subTotal = 0;


        for (OrderItemRequest dto : request.getItems()) {

            // ✅ FIRST validate
            if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Invalid quantity. ItemCode=" + dto.getItemCode() +
                                ", Quantity=" + dto.getQuantity()
                );
            }
            System.out.println(dto);
            if (dto.getUnitPrice() == 0) {
                throw new RuntimeException(
                        "Unit price is null for item: " + dto.getItemCode()
                );
            }

            double itemTotal = dto.getUnitPrice() * dto.getQuantity();
            subTotal += itemTotal;

            OrderItem item = new OrderItem();
            item.setItemCode(dto.getItemCode());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(dto.getUnitPrice());
            item.setTotalPrice(itemTotal);

            order.addOrderItem(item);

            // 🔥 Stock Handling
            switch (dto.getCategory()) {
                case "burger" -> {
                    Burger burger = burgerRepository.findByCode(dto.getItemCode())
                            .orElseThrow(() -> new RuntimeException("Burger not found"));

                    if (burger.getStock() < dto.getQuantity()) {
                        throw new RuntimeException("Not enough stock");
                    }

                    burger.setStock(burger.getStock() - dto.getQuantity());
                    burgerRepository.save(burger);
                }

                case "beverage" -> {
                    Beverages beverage = beveragesRepository.findByCode(dto.getItemCode())
                            .orElseThrow(() -> new RuntimeException("Beverage not found"));

                    if (beverage.getStock() < dto.getQuantity()) {
                        throw new RuntimeException("Not enough stock");
                    }

                    beverage.setStock(beverage.getStock() - dto.getQuantity());
                    beveragesRepository.save(beverage);
                }
            }
        }

    // 4️⃣ Calculate Totals
    double tax = subTotal * 0.1;
    double discount = request.getDiscount() != null ? request.getDiscount() : 0;
    double total = subTotal + tax - discount;

    order.setSubTotal(subTotal);
    order.setTaxAmount(tax);
    order.setDiscountAmount(discount);
    order.setTotalAmount(total);

    // 🔥 SAVE ONLY ORDER (Cascade handles items)
    orderRepository.save(order);

    return order.getInvoiceId();
}

    private String generateInvoiceId() {
        long count = orderRepository.count() + 1;
        return "INV-2026-" + String.format("%04d", count);
    }

    public byte[] generateInvoice(Order order) {

        try {
            Document document = new Document(PageSize.A4, 40, 40, 50, 50);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            // ===== LOGO =====
            Image logo = Image.getInstance(
                    getClass().getResource("/static/logo.png"));
            logo.scaleToFit(100, 100);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

            // ===== TITLE =====
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("MOS Burgers", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Invoice ID: " + order.getInvoiceId()));
            document.add(new Paragraph("Date: " + order.getOrderDate()));
            document.add(new Paragraph("Payment: " + order.getPaymentMethod()));
            document.add(new Paragraph(" "));

            // ===== TABLE =====
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Item Code", headerFont));
            cell.setBackgroundColor(new Color(230, 230, 230));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Qty", headerFont));
            cell.setBackgroundColor(new Color(230, 230, 230));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Unit Price", headerFont));
            cell.setBackgroundColor(new Color(230, 230, 230));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Total", headerFont));
            cell.setBackgroundColor(new Color(230, 230, 230));
            table.addCell(cell);

            List<OrderItem> items =
                    orderItemRepository.findByOrder(order);

            for (OrderItem item : items) {
                table.addCell(item.getItemCode());
                table.addCell(item.getCategory());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getUnitPrice()));
                table.addCell(String.valueOf(item.getTotalPrice()));
            }

            document.add(table);

            document.add(new Paragraph(" "));

            // ===== TOTAL SECTION =====
            Font normalFont = new Font(Font.HELVETICA, 12);
            Font boldFont = new Font(Font.HELVETICA, 14, Font.BOLD);

            document.add(new Paragraph("Sub Total: " + order.getSubTotal(), normalFont));
            document.add(new Paragraph("Tax (10%): " + order.getTaxAmount(), normalFont));
            document.add(new Paragraph("Discount: " + order.getDiscountAmount(), normalFont));
            document.add(new Paragraph(" "));

            Paragraph grandTotal =
                    new Paragraph("Grand Total: " + order.getTotalAmount(), boldFont);
            grandTotal.setAlignment(Element.ALIGN_RIGHT);
            document.add(grandTotal);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            // ===== FOOTER =====
            Font footerFont = new Font(Font.HELVETICA, 10, Font.ITALIC);
            Paragraph footer = new Paragraph(
                    "Thank you for choosing MOS Burgers!\nVisit Again!",
                    footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating invoice PDF");
        }
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Double getDailyRevenue(){

        LocalDate today = LocalDate.now();

        return orderRepository.getRevenueBetween(
                today.atStartOfDay(),
                today.atTime(LocalTime.MAX)
        );
    }

    public Double getWeeklyRevenue(){

        LocalDate today = LocalDate.now();

        LocalDate start = today.with(DayOfWeek.MONDAY);
        LocalDate end = today.with(DayOfWeek.SUNDAY);

        return orderRepository.getRevenueBetween(
                start.atStartOfDay(),
                end.atTime(LocalTime.MAX)
        );
    }

    public Double getMonthlyRevenue(){

        LocalDate today = LocalDate.now();

        LocalDate start = today.withDayOfMonth(1);
        LocalDate end = today.withDayOfMonth(today.lengthOfMonth());

        return orderRepository.getRevenueBetween(
                start.atStartOfDay(),
                end.atTime(LocalTime.MAX)
        );
    }

    public Double getTotalRevenue(){
        return orderRepository.getTotalRevenue();
    }

    // Total Orders
    public Long getTotalOrders() {
        return orderRepository.countTotalOrders();
    }

    // Daily Orders
    public Long getDailyOrders() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        return orderRepository.countOrdersBetween(start, end);
    }
}
