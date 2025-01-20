package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.entity.ExerciseRecord;
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

    public List<ExerciseRecord> getExerciseRecords(Long userId, LocalDate date) {
        return exerciseRecordRepository.findByUserIdAndExerciseDate(userId, date);
    }

    public ExerciseRecord addExerciseRecord(ExerciseRecord record) {
        return exerciseRecordRepository.save(record);
    }
}
