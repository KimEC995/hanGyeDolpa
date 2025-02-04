package com.koreait.hanGyeDolpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {
	
	@GetMapping("login")
	public void login() {
	}
	
//	@GetMapping("exercise")
//	public void exercise() {
//	}
	
	@GetMapping("exercise-add")
	public void exercise_add() {
	}
	
	 @GetMapping("dashboard")
	 public void dashboard() {
	}
}
