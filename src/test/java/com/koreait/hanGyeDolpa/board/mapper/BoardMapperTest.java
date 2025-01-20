package com.koreait.hanGyeDolpa.board.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreait.hanGyeDolpa.board.bean.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardMapperTest {
	@Autowired
	private BoardMapper mapper;
	
	// Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("자동제목11");
		vo.setContent("테스트11");
		vo.setUserId("tester11");
		log.info("BoardVO = " + vo);
		
		// if(mapper.insert(vo)==1) {
		if(mapper.insertSelectKey(vo)==1) {
			log.info("성공!!!------------------");
		}
		else {
			log.info("실패 ㅜㅜㅜㅜ------------------");
		}
		
		log.info("BoardVO = " + vo);
		
//		for(int i=0; i<15; i++) {
//			vo = new BoardVO();
//			vo.setTitle("자동제목"+i);
//			vo.setContent("테스트"+i);
//			vo.setUserId("tester"+i);
//			mapper.insert(vo);
//		}
	}
	
	@Test
	public void testGetList() {
		
		// Lambda 문법
		mapper.getList()
			  .forEach(board -> log.info(board.toString()));
	}
	
	@Test
	public void testGet() {
		int bno = 7;
		BoardVO vo = mapper.get(bno);
		log.info("TestGet() : " + vo);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(9);
		board.setTitle("오운완......");
		board.setContent("3단계 실패..ㅜ");
//		board.setUserId("user123");
		log.info(mapper.update(board)+"건 수정 완료");
	}
}
