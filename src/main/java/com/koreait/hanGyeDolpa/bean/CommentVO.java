package com.koreait.hanGyeDolpa.bean;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentVO {
    private Long cno;       // 댓글 번호
    private Long bno;       // 게시글 번호
    private Long userId; // 댓글 작성자
    private String content; // 댓글 내용
    private Date regdate;  // 작성일
    
    // DBX
    private String userName; //사용자이름
}