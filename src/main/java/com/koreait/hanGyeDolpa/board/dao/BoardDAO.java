package com.koreait.hanGyeDolpa.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.hanGyeDolpa.board.bean.BoardVO;
import com.koreait.hanGyeDolpa.board.mapper.BoardMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper mapper;
	
	// getList
	public List<BoardVO> getList() { return mapper.getList(); }
	public List<BoardVO> getListWithKey(String type, String keyword){
		return mapper.getListWithKey(type, keyword);
	}
	
	// register
	public int register(BoardVO board) {
		return mapper.insert(board);
	}
	
	// read 
	public BoardVO read(int bno) {
		return mapper.get(bno);
	}
	
	// modify
	public int modify(BoardVO board) {
		return mapper.update(board);
	}
	
	// remove
	public int remove(int bno) {
		return mapper.delete(bno);
	}
	
	// viewcnt
	public void updateViewCount(int bno) {
		mapper.updateViewCount(bno);
	}
}
