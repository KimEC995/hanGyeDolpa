package com.koreait.hanGyeDolpa.board.dao;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.hanGyeDolpa.board.mapper.TimeMapper;

@Repository //@Conponent의 자식 Annotaion으로 DAO사
public class TimeDAO {
	
	@Autowired
	private TimeMapper mapper;
	
	public String getTime() {
		Random rd = new Random();
		int num = rd.nextInt(2);
		
		if(num==0) {
			return mapper.getTime1();
		}
		else {
			return mapper.getTime2();
		}
		
	}
}
