package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.entity.Exercise_Table;
import com.koreait.hanGyeDolpa.repository.ExerciseRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    private final ExerciseRecordRepository exerciseRecordRepository;

    public DashboardService(ExerciseRecordRepository exerciseRecordRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
    }

    // 특정 날짜 범위의 총 칼로리 계산
    public int calculateTotalCalories(LocalDate startDate, LocalDate endDate) {
        List<Exercise_Table> records = exerciseRecordRepository.findByExerciseDateBetween(startDate, endDate);
        return records.stream().mapToInt(Exercise_Table::getCalories).sum();
    }

    // 특정 날짜 범위의 총 운동 시간 계산
    public int calculateTotalTime(LocalDate startDate, LocalDate endDate) {
        List<Exercise_Table> records = exerciseRecordRepository.findByExerciseDateBetween(startDate, endDate);
        return records.stream().mapToInt(Exercise_Table::getTimeSpent).sum();
    }
}

