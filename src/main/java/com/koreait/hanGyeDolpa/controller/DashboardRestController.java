package com.koreait.hanGyeDolpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.hanGyeDolpa.dto.checkDataForCalendar;
import com.koreait.hanGyeDolpa.service.DashBoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class DashboardRestController {

	@Autowired
	private DashBoardService dashbService;
	
	@GetMapping("/getCalendarData")
	public List<checkDataForCalendar> getCalendarData(
										@RequestParam String startDate,
										@RequestParam String endDate
										){
		
		log.info("------------ Fetch 호출 -> 시작일: " + startDate + " | 종료일: " + endDate);
		return dashbService.getCalendarData(startDate, endDate);
	}
	
	@GetMapping("/getComboChartData")
	public ResponseEntity<Map<String, Map<Integer, Integer>>> getComboChartData(
										@RequestParam String startDate,
										@RequestParam String endDate
										){
		
		log.info("------------ ComboChart 데이터 로드 중... -> 시작일: " + startDate + "| 종료일: " + endDate);
		Map<String, Map<Integer, Integer>> totalValue = dashbService.getComboData(startDate, endDate);
		
		return ResponseEntity.ok(totalValue);
	}
	
	@GetMapping("/getTotalTimeData")
	public Map<String, Map<String, Integer>> getTotalTimeData(
										@RequestParam String startDate,
										@RequestParam String endDate
										){
		
		return dashbService.getTotlaData(startDate, endDate);
		
	}
	
	@GetMapping("/getHighstScoreData")
	public Map<String, Integer> getHighstScoreData(
										@RequestParam String startDate,
										@RequestParam String endDate
										){
		return dashbService.getHighstScore(startDate, endDate);
	}
	
	
}
