package com.koreait.hanGyeDolpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.hanGyeDolpa.service.UserServiceImpl;

@Controller
public class A_MainController {

	boolean loginFlag = false;
	
	@Autowired
	private UserServiceImpl uService;
	
	@GetMapping("/")
	public String headerTest(Model model) {
		//model.addAttribute("flag", loginFlag);
		
		return "mainPage/headerTest";
	}
	
	@GetMapping("/dashBoard")
	public String dashBoardTest() {
		return "dashboard.html";
	}
	
	@GetMapping("/mapLocation")
	public String mapLocation() {
		return "mapLocation/mapLocation";
	}
	
	@GetMapping("aboutService")
	public String aboutService() {
		return "aboutService.html";
	}
	
//	 @GetMapping("/")
//	 public String dashboard() {
//	     return "dashboard"; // 대시보드 페이지를 반환
//	 }

	 @GetMapping("/exercise")
	 public String exercise() {
	     return "exercise"; // exercise.html을 반환
	 }
	 
	 @GetMapping("/exercise/add")
	 public String addExercise() {
	     return "add-exercise"; // add-exercise.html을 반환
	 }

	 @GetMapping("/about")
	 public String addAbout(){
		return "aboutService"; 
	 }
}