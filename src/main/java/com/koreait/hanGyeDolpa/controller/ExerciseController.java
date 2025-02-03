package com.koreait.hanGyeDolpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.hanGyeDolpa.dto.ExerciseRecordRequest;
import com.koreait.hanGyeDolpa.dto.ExerciseRequest;
import com.koreait.hanGyeDolpa.entity.Exercise;
import com.koreait.hanGyeDolpa.repository.ExerciseRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/exercise")
@Slf4j
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveExerciseRecord(@RequestBody ExerciseRecordRequest request) {
        Exercise record = new Exercise();
        record.setUserNo(request.getUserId());
        record.setClimbPlace(request.getExercisePlace());
        record.setClimbStage(request.getExerciseStage());
        record.setClimbCount(request.getExerciseCount());
        record.setClimbTime(request.getExerciseTime());
        record.setExerciseDate(request.getExerciseDate());
        record.setClimbKcal(request.getExerciseKcal());
        
        exerciseRepository.save(record);
        return ResponseEntity.ok("운동 기록이 저장되었습니다!");
    }
    
    @PostMapping("/records")
    public ResponseEntity<List<Exercise>> getRecords(@RequestBody ExerciseRequest request) {
        // 요청에서 날짜와 사용자 ID를 가져옴
//    	LocalDate localExDate = request.getExerciseDate();
//        Date exerciseDate = localExDate // localdate 
    	String exerciseDate = request.getExerciseDate();
        Long userId = request.getUserId();
        

        log.info(""+userId+ "---"+exerciseDate);
        

        // 데이터베이스에서 기록 조회
       

        List<Exercise> records = exerciseRepository.findByUserNoAndExerciseDate(userId, exerciseDate);
        log.info(""+records);
        return ResponseEntity.ok(records);
    }
    
    
}
