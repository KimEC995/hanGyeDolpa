package com.koreait.hanGyeDolpa.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper // MyBaatis 연결이랑 관련있
public interface TimeMapper {
	
	@Select("SELECT SYSDATE() FROM DUAL")
	public String getTime1();
	
	
	public String getTime2();
}
