package org.example.service;

import org.example.model.dto.TopSellingItemDTO;
import org.example.model.entity.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderIemService {

    @Autowired
    private OrderItemRepository orderItemRepository ;


    public List<TopSellingItemDTO> getDailyTopSelling() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        return orderItemRepository.findTopSellingItems(start, end, (Pageable) PageRequest.of(0,10));

    }

    public List<TopSellingItemDTO> getWeeklyTop10(){

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        return orderItemRepository.findTopSellingItems(
                startOfWeek.atStartOfDay(),
                endOfWeek.atTime(LocalTime.MAX),
                (Pageable) PageRequest.of(0,10)
        );
    }

    public List<TopSellingItemDTO> getMonthlyTop10() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        LocalDate lastDay = today.withDayOfMonth(today.lengthOfMonth());

        LocalDateTime start = firstDay.atStartOfDay();
        LocalDateTime end = lastDay.atTime(LocalTime.MAX);

        return orderItemRepository.findTopSellingItems(
                start,
                end,
                PageRequest.of(0, 10) // Top 10
        );
    }

    public List<TopSellingItemDTO> dailyTop() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        return orderItemRepository.findTopSellingItems(start, end, (Pageable) PageRequest.of(0,1));

    }

    public List<TopSellingItemDTO> weeklyTop(){

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        return orderItemRepository.findTopSellingItems(
                startOfWeek.atStartOfDay(),
                endOfWeek.atTime(LocalTime.MAX),
                (Pageable) PageRequest.of(0,1)
        );
    }

    public List<TopSellingItemDTO> monthlyTop() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        LocalDate lastDay = today.withDayOfMonth(today.lengthOfMonth());

        LocalDateTime start = firstDay.atStartOfDay();
        LocalDateTime end = lastDay.atTime(LocalTime.MAX);

        return orderItemRepository.findTopSellingItems(
                start,
                end,
                PageRequest.of(0, 1) // Top 10
        );
    }

    public List<TopSellingItemDTO> totalyTop() {

        return orderItemRepository.findBestItem(PageRequest.of(0,1));
    }

    public List<TopSellingItemDTO> getTotalTop10() {
        return orderItemRepository.findBestItem(
                PageRequest.of(0, 10) // Top 10
        );
    }
}
