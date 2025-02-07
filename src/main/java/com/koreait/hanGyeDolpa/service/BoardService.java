package com.koreait.hanGyeDolpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.hanGyeDolpa.bean.BoardVO;
import com.koreait.hanGyeDolpa.dao.BoardDAO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

	@Autowired
	private BoardDAO bDao;
	
	private Long getBoardWriterId(Long bNo) {
		return bDao.getUserIDinBoard(bNo);
	}
	
	private int removeBoardData(Long bNo) {
		return bDao.remove(bNo);
	}
	
	public boolean checkUserRight(Long bNo, HttpSession session) {
		boolean flag = false;
		Long logInUser = (Long) session.getAttribute("uNo");
		Long writerUser = getBoardWriterId(bNo);
		
		if(logInUser == writerUser) {flag = true;}
		
		return flag;
	}
	
	public String checkDelete(Long bNo, HttpSession session) {
		boolean flag = checkUserRight(bNo, session);

		String msg = "글삭제: ";
		
		if(flag) {
			msg += removeBoardData(bNo) > 0 ? "성공" : "실패";
		}
		else {
			msg += "작성자 본인만 삭제 가능합니다~\n로그인 정보를 확인하세요";
		}
		
		return msg;
	}
	
	public int boardRegister(BoardVO vo) {
		log.info("서비스 접근");
		return bDao.register(vo);
	}
	
	public BoardVO readBoard(Long bno) {
		return bDao.getAllDataAndUserName(bno);
	}
	
	public String modifyBoard(BoardVO vo, HttpSession session) {
		
		boolean flag = checkUserRight(vo.getBno(), session);

		String msg = "글 수정: ";
		
		if(flag) {
			msg += bDao.modify(vo) > 0 ? "성공" : "실패";
		}
		else {
			msg += "작성자 본인만 수정 가능합니다~\n로그인 정보를 확인하세요";
		}
		
		return msg;
	}
}
