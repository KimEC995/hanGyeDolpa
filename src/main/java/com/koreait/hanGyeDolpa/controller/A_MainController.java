package com.koreait.hanGyeDolpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class A_MainController {

	@GetMapping("/")
	public String headerTest(Long userNo, HttpSession session) {
		//model.addAttribute("flag", loginFlag);
		
		Long uNo = (Long) session.getAttribute("uNo");
		if(uNo == null || uNo == 0L) {
			session.setAttribute("uNo", 0L);
		}
		
		return "mainPage/headerTest";
	}
	
	@GetMapping("/dashBoard")
	public String dashBoardTest(HttpSession session, RedirectAttributes rttr) {
		// 유저 번호 가져오기
		Long uNo = (Long) session.getAttribute("uNo");
		
		// 만약 로갓상태면 로그인 페이지로 이동하기
		if(uNo == 0L || uNo == null) {
			log.info("비로긴 대시보드 접근! 세션 로긴값: " + uNo);
			rttr.addFlashAttribute("msg", "로그인 정보가 없습니다!\n로그인 페이지로 이동합니다.");
			
			return "redirect:/login/page";
		}
		else {
			return "dashboard.html";
		}

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
}