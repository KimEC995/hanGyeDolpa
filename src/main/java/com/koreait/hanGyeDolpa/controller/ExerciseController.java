package com.koreait.hanGyeDolpa.controller;

import com.koreait.hanGyeDolpa.entity.Exercise_Table;
import com.koreait.hanGyeDolpa.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addExerciseRecord(@RequestBody Map<String, Object> payload) {
        try {
            LocalDate exerciseDate = LocalDate.parse((String) payload.get("exerciseDate"));
            String location = (String) payload.get("location");
            int difficulty = getIntFromPayload(payload, "difficulty");
            int count = getIntFromPayload(payload, "count");
            int calories = getIntFromPayload(payload, "calories");
            int timeSpent = getIntFromPayload(payload, "timeSpent");

            // Exercise 객체 생성 및 저장
            Exercise_Table record = new Exercise_Table(exerciseDate, location, difficulty, count, calories, timeSpent);
            Exercise_Table savedRecord = exerciseService.addExerciseRecord(record);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "운동 기록이 성공적으로 등록되었습니다.", "id", savedRecord.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "운동 기록 등록에 실패했습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<Exercise_Table>> getExerciseRecords(@RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Exercise_Table> exerciseRecords = exerciseService.getExerciseRecords(localDate);
            return ResponseEntity.ok(exerciseRecords);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/records/month")
    public ResponseEntity<Map<String, Integer>> getMonthlyStats(@RequestParam String month) {
        try {
            LocalDate startDate = LocalDate.parse(month + "-01");
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);

            // Service를 통해 운동 기록 조회
            List<Exercise_Table> records = exerciseService.getExerciseRecordsBetween(startDate, endDate);

            // 총 칼로리와 총 시간 계산
            int totalCalories = records.stream().mapToInt(Exercise_Table::getCalories).sum();
            int totalTime = records.stream().mapToInt(Exercise_Table::getTimeSpent).sum();

            // 결과 반환
            return ResponseEntity.ok(Map.of("calories", totalCalories, "time", totalTime));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    private int getIntFromPayload(Map<String, Object> payload, String key) {
        Object value = payload.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            return Integer.parseInt((String) value);
        }
        throw new IllegalArgumentException(key + " 값이 유효하지 않습니다.");
    }
}




