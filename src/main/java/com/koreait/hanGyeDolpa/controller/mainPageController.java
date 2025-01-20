package com.koreait.hanGyeDolpa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.hanGyeDolpa.bean.UserVO;
import com.koreait.hanGyeDolpa.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class mainPageController {
	
	boolean loginFlag = false;
	
	@Autowired
	private UserServiceImpl uService;
	
	@GetMapping("headerTest")
	public String headerTest(Model model) {
		//model.addAttribute("flag", loginFlag);
		
		return "mainPage/headerTest";
	}
	
	@GetMapping("/mainHeaderCheckUserLogin")
	@ResponseBody
	public Map<String, Object> responseTest() {
		
		Map<String, Object> resp = new HashMap<>();
		
		boolean flag = loginFlag;
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
	
	@PostMapping("loginTest")
	public String loginTestPOST() {
		loginFlag = uService.makeUserLogin(loginFlag, true);
		return "redirect:headerTest";
	}
	
	@GetMapping("dashBoardTest")
	public String dashBoardTest() {
		return "Sample_DashBoard.html";
	}
	
	@GetMapping("userProfileTest")
	public String userProfileTest() {
		return "Sample_UserProfile.html";
	}
	
	@PostMapping("userProfileTest")
	public String userProfileTestPOST() {
		loginFlag= uService.makeUserLogin(loginFlag, false);
		return "redirect:headerTest";
	}
	
	@GetMapping("aboutService")
	public String aboutService() {
		return "aboutService.html";
	}
	
	@GetMapping("eventPage")
	public String eventPage() {
		return "Sample_eventPage.html";
	}
}
