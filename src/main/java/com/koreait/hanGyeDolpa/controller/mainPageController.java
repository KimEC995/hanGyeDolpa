package com.koreait.hanGyeDolpa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.hanGyeDolpa.bean.UserVO;
import com.koreait.hanGyeDolpa.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class mainPageController {
	
	@Autowired
	private UserServiceImpl uService;
	
	private boolean checkUserLogin() {
		
		boolean flag = uService.makeUserLogin();
		
		return flag;
	}
	
	@GetMapping("headerTest")
	public String headerTest(Model model) {
		boolean loginFlag = false;
		model.addAttribute("flag", loginFlag);
		
		return "mainPage/headerTest";
	}
	
	@GetMapping("/mainHeaderCheckUserLogin")
	@ResponseBody
	public Map<String, Object> responseTest() {
		
		Map<String, Object> resp = new HashMap<>();
		
		boolean flag = checkUserLogin();
		resp.put("flag", flag);
		
		if(flag) {
			UserVO vo = uService.makeSampleUserData();
			resp.put("user", vo);
		}
		resp.put("flag", flag);
		
		return resp;
	}
	
	@GetMapping("communityTest")
	public String communityTest() {
		return "Sample_Community.html";
	}
	
	@GetMapping("loginTest")
	public String loginTest() {
		return "Sample_Login.html";
	}
	
	@GetMapping("dashBoardTest")
	public String dashBoardTest() {
		return "Sample_DashBoard.html";
	}
	
	@GetMapping("locationTest")
	public String locationTest() {
		return "Sample_Loacation.html";
	}
	
	@GetMapping("userProfileTest")
	public String userProfileTest() {
		return "Sample_UserProfile.html";
	}
	
	@GetMapping("aboutServicePage")
	public String aboutServicePage() {
		return "aboutService.html";
	}
}
