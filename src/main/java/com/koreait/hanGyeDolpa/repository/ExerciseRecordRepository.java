package com.koreait.hanGyeDolpa.repository;

import com.koreait.hanGyeDolpa.entity.Exercise_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<Exercise_Table, Long> {

    // 특정 날짜의 운동 기록 조회
    List<Exercise_Table> findByExerciseDate(LocalDate exerciseDate);

    // 날짜 범위로 운동 기록 조회
    List<Exercise_Table> findByExerciseDateBetween(LocalDate startDate, LocalDate endDate);
}


