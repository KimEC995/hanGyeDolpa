package com.koreait.hanGyeDolpa.controller;

import com.koreait.hanGyeDolpa.entity.Exercise;
import com.koreait.hanGyeDolpa.entity.User;
import com.koreait.hanGyeDolpa.service.ExerciseService;
import com.koreait.hanGyeDolpa.service.UserService;
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
    private final UserService userService;

    public ExerciseController(ExerciseService exerciseService, UserService userService) {
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addExerciseRecord(@RequestBody Map<String, Object> payload) {
        try {
            String exerciseType = (String) payload.get("exerciseType");
            LocalDate exerciseDate = LocalDate.parse((String) payload.get("exerciseDate"));
            String location = (String) payload.get("location");
            int difficulty = getIntFromPayload(payload, "difficulty");
            int count = getIntFromPayload(payload, "count");
            int calories = getIntFromPayload(payload, "calories");
            int timeSpent = getIntFromPayload(payload, "timeSpent");
            Long userId = getLongFromPayload(payload, "userId");

            User user = userService.findById(userId);
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "유효하지 않은 사용자입니다."));
            }

            Exercise record = new Exercise(exerciseType, location, exerciseDate, difficulty, count, calories, timeSpent, user);
            Exercise savedRecord = exerciseService.addExerciseRecord(record);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "운동 기록이 성공적으로 등록되었습니다.", "id", savedRecord.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "운동 기록 등록에 실패했습니다: " + e.getMessage()));
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<Exercise>> getExerciseRecords(@RequestParam String date, @RequestParam Long userId) {
        try {
            LocalDate localDate = LocalDate.parse(date); // 요청 날짜 파싱
            List<Exercise> exerciseRecords = exerciseService.getExerciseRecords(userId, localDate);
            return ResponseEntity.ok(exerciseRecords); // 운동 기록 반환
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null); // 오류 응답
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

    private Long getLongFromPayload(Map<String, Object> payload, String key) {
        Object value = payload.get(key);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            return Long.parseLong((String) value);
        }
        throw new IllegalArgumentException(key + " 값이 유효하지 않습니다.");
    }
}


