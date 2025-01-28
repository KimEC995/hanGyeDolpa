package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.entity.Exercise_Table;
import com.koreait.hanGyeDolpa.repository.ExerciseRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRecordRepository exerciseRecordRepository;

    public ExerciseService(ExerciseRecordRepository exerciseRecordRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
    }

    // 특정 날짜의 운동 기록 조회
    public List<Exercise_Table> getExerciseRecords(LocalDate date) {
        return exerciseRecordRepository.findByExerciseDate(date);
    }

    // 특정 날짜 범위의 운동 기록 조회
    public List<Exercise_Table> getExerciseRecordsBetween(LocalDate startDate, LocalDate endDate) {
        return exerciseRecordRepository.findByExerciseDateBetween(startDate, endDate);
    }

    // 운동 기록 추가
    public Exercise_Table addExerciseRecord(Exercise_Table record) {
        return exerciseRecordRepository.save(record);
    }
}



