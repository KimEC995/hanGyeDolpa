package com.koreait.hanGyeDolpa.board.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO { 
    private int bno;         	 // 게시글 번호
    private String title;   	 // 게시글 제목
    private String content; 	 // 게시글 내용
    private Date regdate;      // 게시글 작성 날짜
    private Date updatedate;   // 수정
    private String userId;       // 작성자 ID
    private int boardCnt;        // 게시글 조회수
}
