package com.koreait.hanGyeDolpa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.hanGyeDolpa.dto.checkDataForCalendar;
import com.koreait.hanGyeDolpa.entity.Exercise;
import com.koreait.hanGyeDolpa.repository.ExerciseRepository;

@Service
public class DashboardService {

	@Autowired
	private ExerciseRepository exRepo;

	// 달력 그래프 테스트 함수(삭제하기)
	public List<checkDataForCalendar> checkData(String startDate, String endDate){
	    
		List<checkDataForCalendar> result = exRepo.findDataForCalendar(startDate, endDate);
		result.forEach(data -> System.out.println("------------=> Returned data: " + data));
		return result;
	}
	
	// 1. 전체 데이터 호출 함수
    private List<Exercise> getAllExercises(String startDate, String endDate) {
        return exRepo.findAllByDateRange(startDate, endDate);
    }
    
    //1-1. 달력용
    public List<checkDataForCalendar> getCalendarData(String startDate, String endDate) {

    	List<Exercise> exercises = getAllExercises(startDate, endDate);

        Map<String, Long> groupedData = exercises.stream()
            .collect(Collectors.groupingBy(Exercise::getExerciseDate, Collectors.counting()));

        return groupedData.entrySet().stream()
            .map(entry -> new checkDataForCalendar(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }
    
    //1-2. 이번달 운동 시간 총량
    public Map<String, Map<String, Integer>> getTotlaData(String startDate, String endDate) {
        
    	List<Exercise> exercises = exRepo.findAllByDateRange(startDate, endDate);

    	// 월별 그룹
        Map<String, List<Exercise>> groupedByMonth = exercises.stream()
            .collect(Collectors.groupingBy(
                exercise -> exercise.getExerciseDate().substring(0, 7) // 월별 그룹화
            ));
        
        // 월별 계산 시작
        Map<String, Map<String, Integer>> totalData = new HashMap<>();
        for (Map.Entry<String, List<Exercise>> entry : groupedByMonth.entrySet()) {
            String month = entry.getKey();
            List<Exercise> monthlyExercises = entry.getValue();

            // 횟수 | 시간 총합
            int totalClimbCount = monthlyExercises.stream()
                .mapToInt(Exercise::getClimbCount)
                .sum();
            int totalClimbTime = monthlyExercises.stream()
                .mapToInt(Exercise::getClimbTime)
                .sum();

            // 결과 맵에 추가
            Map<String, Integer> resultMap = new HashMap<>();
            resultMap.put("ClimbCount", totalClimbCount);
            resultMap.put("ClimbTime", totalClimbTime);

            totalData.put(month, resultMap);
        }

        return totalData;
    }
    
    //1-3. 이번달 운동 난이도
    public Map<String, Map<Integer, Integer>> getComboData(String startDate, String endDate) {
        
    	List<Exercise> exercises = exRepo.findAllByDateRange(startDate, endDate);

        return exercises.stream()
                .collect(Collectors.groupingBy(
                        exercise -> exercise.getExerciseDate().substring(0, 7),
                        Collectors.groupingBy(
                            Exercise::getClimbStage,
                            Collectors.summingInt(e -> 1)//숫자세기
                        )
                    ));
    }
    
    //1-4. 개인 최고 기록 가져오기
    public Map<String, Integer> getHighstScore(String startDate, String endDate){
        List<Exercise> exercises = exRepo.findAllByDateRange(startDate, endDate);

        int highestClimbStage = exercises.stream()
                .mapToInt(Exercise::getClimbStage)
                .max()
                .orElse(0); // 없으면 0
        
        int highestClimbTime = exercises.stream()
                .mapToInt(Exercise::getClimbTime)
                .max()
                .orElse(0); // 없으면 0
        
        Map<String, Integer> highestScores = new HashMap<>();
        highestScores.put("stage", highestClimbStage);
        highestScores.put("time", highestClimbTime);

        return highestScores;
    }
}