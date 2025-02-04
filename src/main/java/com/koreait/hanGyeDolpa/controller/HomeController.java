package com.koreait.hanGyeDolpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	 @GetMapping("/")
	 public String dashboard() {
	     return "dashboard"; // 대시보드 페이지를 반환
	 }

	 @GetMapping("/exercise")
	 public String exercise() {
	     return "exercise"; // exercise.html을 반환
	 }
	 
	 @GetMapping("/exercise/add")
	 public String addExercise() {
	     return "add-exercise"; // add-exercise.html을 반환
	 }
}