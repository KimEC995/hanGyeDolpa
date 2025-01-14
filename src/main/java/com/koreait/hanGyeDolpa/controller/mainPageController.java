package com.koreait.hanGyeDolpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class mainPageController {
	
	@GetMapping("headerTest")
	public String headerTest() {
		return "mainPage/headerTest.html";
	}
	
	@GetMapping("communityTest")
	public String communityTest() {
		return "Sample_Community.html";
	}
}
