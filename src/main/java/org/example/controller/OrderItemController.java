package org.example.controller;

import org.example.model.dto.TopSellingItemDTO;
import org.example.model.entity.OrderItem;
import org.example.service.OrderIemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order-item")
@CrossOrigin("http://localhost:4200/")
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

    @GetMapping("/top-1/daily")
    public List<TopSellingItemDTO> dailyTop(){
        return orderIemService.dailyTop();
    }

    @GetMapping("/top-1/weekly")
    public List<TopSellingItemDTO> weeklyTop(){
        return orderIemService.weeklyTop();
    }

    @GetMapping("/top-1/monthly")
    public List<TopSellingItemDTO> monthlyTop(){
        return orderIemService.monthlyTop();
    }

    @GetMapping("/top-1/total")
    public List<TopSellingItemDTO> totalTop(){
        return orderIemService.totalyTop();
    }
//    @GetMapping("/top-1/m")
//    public List<TopSellingItemDTO> top(){
//        return orderIemService.getTopSellingItemsWithTie();
//    }

}
