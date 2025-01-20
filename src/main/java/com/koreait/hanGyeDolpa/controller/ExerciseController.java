package com.koreait.hanGyeDolpa.controller;

import com.koreait.hanGyeDolpa.entity.ExerciseRecord;
import com.koreait.hanGyeDolpa.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
            String exerciseType = (String) payload.get("exerciseType");
            LocalDate exerciseDate = LocalDate.parse((String) payload.get("exerciseDate"));
            String location = (String) payload.get("location");
            int difficulty = Integer.parseInt((String) payload.get("difficulty"));
            int calories = Integer.parseInt((String) payload.get("calories"));
            int timeSpent = Integer.parseInt((String) payload.get("timeSpent"));

            // 사용자 정보를 null로 설정
            ExerciseRecord record = new ExerciseRecord(exerciseType, exerciseDate, location, difficulty, calories, timeSpent, null);
            ExerciseRecord savedRecord = exerciseService.addExerciseRecord(record);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "운동 기록이 성공적으로 등록되었습니다.", "id", savedRecord.getId()));
        } catch (Exception e) {
            e.printStackTrace(); // 예외 스택 트레이스 출력
            return ResponseEntity.badRequest().body(Map.of("message", "운동 기록 등록에 실패했습니다: " + e.getMessage()));
        }
    }
}







