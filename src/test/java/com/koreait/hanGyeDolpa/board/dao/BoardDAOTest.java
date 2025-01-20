package com.koreait.hanGyeDolpa.board.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreait.hanGyeDolpa.board.bean.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardDAOTest {
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testRegister() {
		BoardVO board =new BoardVO();
		board.setTitle("DAO Test");
		board.setContent("for DAO Test");
		board.setUserId("DAO Tester");
		dao.register(board);
		log.info(""+board);
	}
	
	@Test
	public void testetList() {
		dao.getList()
		   .forEach(board -> log.info(""+board));
	}
	
	@Test
	public void testRead() {
		int bno = 7;
		BoardVO vo = dao.read(bno);
		log.info(""+vo);
		
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(10);
		vo.setTitle("DAO에서 수정!");
		vo.setContent("...ㅇ.ㅇ.ㅇ.ㅇ.ㅇ.ㅇ.");
		
		dao.modify(vo);
	}
	
	
}
