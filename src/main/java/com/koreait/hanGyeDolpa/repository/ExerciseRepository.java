package com.koreait.hanGyeDolpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.koreait.hanGyeDolpa.dto.checkDataForCalendar;
import com.koreait.hanGyeDolpa.entity.Exercise;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	List<Exercise> findByUserNoAndExerciseDate(Long userNo, String exerciseDate);
	
	//@Query("SELECT e FROM Exercise e WHERE e.userNo = :userNo AND e.exerciseDate = :exerciseDate")
	//List<Exercise> findByUserNoAndExerciseDate(@Param("userNo") Long userNo, @Param("exerciseDate") LocalDate exerciseDate);

	Exercise findByExerciseDate(String exerciseDate);

	//캘린더전용 -> 테스트 후 아래와 겹치니 삭제할 것
	@Query("""
		    SELECT new com.koreait.hanGyeDolpa.dto.checkDataForCalendar(
		        a.exerciseDate,
		        COUNT(a) 
		    ) 
		    FROM Exercise a 
		    WHERE a.exerciseDate BETWEEN :startDate AND :endDate 
		    GROUP BY a.exerciseDate
		""")
		List<checkDataForCalendar> findDataForCalendar(
		    @Param("startDate") String startDate,
		    @Param("endDate") String endDate
		);
		
		// 전체 데이터 전부 다(날짜기준)
		@Query("SELECT e FROM Exercise e WHERE e.exerciseDate BETWEEN :startDate AND :endDate")
		List<Exercise> findAllByDateRange(
		    @Param("startDate") String startDate,
		    @Param("endDate") String endDate
		);


}