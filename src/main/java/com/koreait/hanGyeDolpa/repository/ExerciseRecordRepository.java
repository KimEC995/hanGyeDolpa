package com.koreait.hanGyeDolpa.repository;

import com.koreait.hanGyeDolpa.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByUserIdAndExerciseDate(Long userId, LocalDate exerciseDate);
    
    List<Exercise> findByUserIdAndExerciseDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // 날짜로 운동 기록 조회 메서드 추가
    List<Exercise> findByExerciseDate(LocalDate exerciseDate);
}

