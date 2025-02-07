package com.koreait.hanGyeDolpa.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO { 
    private Long bno;         	 // 게시글 번호
    private String title;   	 // 게시글 제목
    private String content; 	 // 게시글 내용
    private Date regdate;      // 게시글 작성 날짜
    private Date updatedate;   // 수정
    private Long userId;       // 작성자 번호
    private int boardCnt;        // 게시글 조회수
    private String userName;	// 작성자이름(DB엔X)
}