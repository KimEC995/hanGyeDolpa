package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.entity.Exercise;
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

    public int calculateTotalCalories(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Exercise> records = exerciseRecordRepository.findByUserIdAndExerciseDateBetween(userId, startDate, endDate);
        return records.stream().mapToInt(Exercise::getCalories).sum();
    }

    public int calculateTotalTime(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Exercise> records = exerciseRecordRepository.findByUserIdAndExerciseDateBetween(userId, startDate, endDate);
        return records.stream().mapToInt(Exercise::getTimeSpent).sum();
    }
}
