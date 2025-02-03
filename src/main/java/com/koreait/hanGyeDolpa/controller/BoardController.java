package com.koreait.hanGyeDolpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.koreait.hanGyeDolpa.bean.BoardVO;
import com.koreait.hanGyeDolpa.dao.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	// register 화면 호출
	@GetMapping("register")
	public void register() {
		
	}
	
	// register 처리
	@PostMapping("register")
	public RedirectView write(BoardVO board, RedirectAttributes rttr) {
		log.info("" + board);
		log.info(dao.register(board)+"건 등록");
		
		rttr.addFlashAttribute("msg", "글등록 완료");
		
		return new RedirectView("list");
	}
	
	
	// List
	@GetMapping("list")
	public void list(String type, String keyword, Model model) {
		log.info("---------------------------------->");
		log.info("Get List Called");
		
		if(keyword == null) {
			model.addAttribute("list", dao.getList());
		}
		else {
			model.addAttribute("list",dao.getListWithKey(type, keyword));
		}
	}
	
	// read(글읽기) 처리용
	// localhost:10000/board/read?=N을 호출했을 때 내용을 보여주는 페이지
	@RequestMapping("read")
	public void read(int bno, Model model) {
		log.info("---------------------------->");
		log.info("read : bno = " + bno);
		// 조회수 증가
		dao.updateViewCount(bno);
		
		// 게시글 정보 가져오기
		model.addAttribute("vo", dao.read(bno));
	}
	
	// remove 처리용
	// localhost:10000/board/remove?bno=N
	@RequestMapping("remove")
	public RedirectView remove(int bno,RedirectAttributes rttr) {
		log.info("---------------------------->");
//		log.info("삭제 건수 : " + dao.remove(bno));
		
		if(dao.remove(bno)>0) {
			rttr.addFlashAttribute("msg","글 삭제 성공");
		}
		else {
			rttr.addFlashAttribute("msg","오류");
		}
		
		return new RedirectView("list");
	}
	
	// modify 처리
	// localhost:10000/board/modify?bno=N -> 페이지 호출
	@GetMapping("modify")
	public void modify(int bno, Model model) {
		// bno로 BoardVO를 얻어오고 model에 태우는 과정 필요
		model.addAttribute("board", dao.read(bno));
	}
	
	@PostMapping("modify")
	public RedirectView modify(BoardVO board, RedirectAttributes rttr) {
		
		if(dao.modify(board) > 0) {
			rttr.addFlashAttribute("msg",board.getBno()+"번 글 수정완료");
		}
		else {
			rttr.addFlashAttribute("msg","글 수정실패");
		}
		
		return new RedirectView("list");
	}
	
	
	
}