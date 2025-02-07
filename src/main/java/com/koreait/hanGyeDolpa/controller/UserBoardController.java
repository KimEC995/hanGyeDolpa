package com.koreait.hanGyeDolpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.hanGyeDolpa.bean.BoardVO;
import com.koreait.hanGyeDolpa.service.BoardService;
import com.koreait.hanGyeDolpa.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/user")
@Slf4j
public class UserBoardController {
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private UserService uService;
	
	// register 화면 호출
	@GetMapping("register")
	public String register(HttpSession session, RedirectAttributes rttr ) {
		boolean flag = uService.checkUserLogin(session);
		
		if(flag) {
			Long uNo = (Long) session.getAttribute("uNo");
			return "/board/register";
		}
		
		else {
			rttr.addFlashAttribute("msg", "로그인 정보가 없습니다.\n로그인 후 작성해주세요");
			return "redirect:/board/list";
		}
	}
	
	@PostMapping("register")
	public String write(BoardVO board, RedirectAttributes rttr) {
		log.info("" + board);
		log.info(bService.boardRegister(board)+"건 등록");
		
		rttr.addFlashAttribute("msg", "글등록 완료");
		
		return "redirect:/board/list";
	}
	
	// localhost:10000/board/remove?bno=N
	@RequestMapping("remove")
	public String remove(Long bno, RedirectAttributes rttr, HttpSession session) {
		
		String msg = bService.checkDelete(bno, session);
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/list";
	}
	
	// localhost:10000/board/modify?bno=N -> 페이지 호출
	@GetMapping("modify")
	public String modify(Long bno, Model model, HttpSession session, RedirectAttributes rttr) {
		boolean flag = bService.checkUserRight(bno, session);
		
		if(flag) {
			model.addAttribute("board", bService.readBoard(bno));
			return "/board/modify";
		}
		
		else {
			rttr.addFlashAttribute("msg", "로그인 정보가 올바르지 않습니다.\n로그인 정보를 확인해주세요");
			return "redirect:/board/list";
		}
		
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, RedirectAttributes rttr, HttpSession session) {
		String msg = bService.modifyBoard(board, session);
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/list";
	}
}
