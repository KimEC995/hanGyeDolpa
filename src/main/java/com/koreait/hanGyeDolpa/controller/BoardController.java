package com.koreait.hanGyeDolpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.hanGyeDolpa.dao.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardDAO dao;

	// List
	@GetMapping("list")
	public void list(String type, String keyword, Model model) {
		log.info("보드 리스트 호출 시작.");
		log.info("Type -> " + type + " -> Keyword -> "+ keyword);
		if(keyword == null) {
			// 사용자 번호
//			model.addAttribute("list", dao.getList());
			// 사용자 이름
			model.addAttribute("list", dao.getUserNameList());
		}
		else {
			// 사용자 번호
//			model.addAttribute("list",dao.getListWithKey(type, keyword));
			// 사용자 이름
			log.info(dao.getUserNameListWithKey(type, keyword).toString());
			model.addAttribute("list",dao.getUserNameListWithKey(type, keyword));
		}
		
		log.info("보드 리스트 호출 완료.");
	}
	
	// read(글읽기) 처리용
	// localhost:10000/board/read?=N을 호출했을 때 내용을 보여주는 페이지
	@RequestMapping("read")
	public void read(Long bno, Model model) {
		log.info("read : bno = " + bno);
		
		// 조회수 증가
		dao.updateViewCount(bno);
		
		// 게시글 정보 가져오기
		model.addAttribute("vo", dao.getAllDataAndUserName(bno));
	}
}
