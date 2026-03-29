package org.example.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.example.model.entity.Order;
import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public byte[] generateInvoice(Order order) {

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("MOS Burgers Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Invoice ID: " + order.getInvoiceId()));
            document.add(new Paragraph("Date: " + order.getOrderDate()));
            document.add(new Paragraph("Payment Method: " + order.getPaymentMethod()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Item Code");
            table.addCell("Quantity");
            table.addCell("Unit Price");
            table.addCell("Total");

            List<OrderItem> items =
                    orderItemRepository.findByOrder(order);

            for (OrderItem item : items) {
                table.addCell(item.getItemCode());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getUnitPrice()));
                table.addCell(String.valueOf(item.getTotalPrice()));
            }

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Sub Total: " + order.getSubTotal()));
            document.add(new Paragraph("Tax: " + order.getTaxAmount()));
            document.add(new Paragraph("Discount: " + order.getDiscountAmount()));
            document.add(new Paragraph("Total: " + order.getTotalAmount()));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating invoice");
        }
    }
}