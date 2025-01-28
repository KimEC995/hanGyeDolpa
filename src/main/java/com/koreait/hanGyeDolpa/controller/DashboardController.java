package com.koreait.hanGyeDolpa.controller;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.hanGyeDolpa.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<int[]> getMonthlyStats(@RequestParam Long userId, @RequestParam String month) {
        try {
            LocalDate startDate = LocalDate.parse(month + "-01");
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);
            int totalCalories = dashboardService.calculateTotalCalories(userId, startDate, endDate);
            int totalTime = dashboardService.calculateTotalTime(userId, startDate, endDate);
            return new ResponseEntity<>(new int[]{totalCalories, totalTime}, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

