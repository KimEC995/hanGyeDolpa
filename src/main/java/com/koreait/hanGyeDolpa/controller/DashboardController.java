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
    public ResponseEntity<int[]> getMonthlyStats(@RequestParam String month) {
        try {
            // 해당 월의 시작 날짜와 마지막 날짜 계산
            LocalDate startDate = LocalDate.parse(month + "-01");
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);

            // 총 칼로리와 총 시간 계산
            int totalCalories = dashboardService.calculateTotalCalories(startDate, endDate);
            int totalTime = dashboardService.calculateTotalTime(startDate, endDate);

            // 결과 반환
            return new ResponseEntity<>(new int[]{totalCalories, totalTime}, HttpStatus.OK);
        } catch (Exception e) {
            // 오류 발생 시 BAD_REQUEST 반환
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}


