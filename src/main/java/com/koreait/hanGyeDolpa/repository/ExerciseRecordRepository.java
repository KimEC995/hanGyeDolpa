package com.koreait.hanGyeDolpa.repository;

import com.koreait.hanGyeDolpa.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    List<ExerciseRecord> findByUserIdAndExerciseDate(Long userId, LocalDate exerciseDate);
    
    List<ExerciseRecord> findByUserIdAndExerciseDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
