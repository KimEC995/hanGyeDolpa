package com.koreait.hanGyeDolpa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.hanGyeDolpa.entity.Exercise;
import com.koreait.hanGyeDolpa.repository.ExerciseRecordRepository;

@Service
public class ExerciseService {

    private final ExerciseRecordRepository exerciseRecordRepository;

    public ExerciseService(ExerciseRecordRepository exerciseRecordRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
    }

    public List<Exercise> getExerciseRecords(Long userId, LocalDate date) {
        return exerciseRecordRepository.findByUserIdAndExerciseDate(userId, date);
    }

    // 이게 저장인듯?
    public Exercise addExerciseRecord(Exercise record) {
        return exerciseRecordRepository.save(record);
    }

    // 날짜로 운동 기록 조회 메서드 추가
    public List<Exercise> findByDate(LocalDate date) {
        return exerciseRecordRepository.findByExerciseDate(date);
    }
}

