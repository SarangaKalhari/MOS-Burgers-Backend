package org.example.controller;

import org.example.model.dto.TopSellingItemDTO;
import org.example.model.entity.OrderItem;
import org.example.service.OrderIemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order-item")
public class OrderItemController {

    @Autowired
    public OrderIemService orderIemService ;

    @GetMapping("/top/daily")
    public List<TopSellingItemDTO> dailyTopSelling(){
        return orderIemService.getDailyTopSelling();
    }

    @GetMapping("top/weekly")
    public List<TopSellingItemDTO> weeklyTop10(){
        return orderIemService.getWeeklyTop10();
    }

    @GetMapping("/top/monthly")
    public List<TopSellingItemDTO> monthlyTopSelling(){
        return orderIemService.getMonthlyTop10();
    }

}
