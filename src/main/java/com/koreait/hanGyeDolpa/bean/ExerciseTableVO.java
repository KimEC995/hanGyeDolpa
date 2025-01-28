package com.koreait.hanGyeDolpa.bean;

import lombok.Data;

@Data
public class ExerciseTableVO {
	
	private Long climbNo;
	private Long userNo;
	private String climbDate;
	private String climbPlace;
	private int climbStage;
	private int climbCount;
	private int climbCalories;
	private int climbTime;
}
