package com.koreait.hanGyeDolpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_TABLE") // 데이터베이스 테이블 이름과 일치
public class User_Table {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO") // 데이터베이스의 기본 키 필드와 매핑
    private Long uid;

    @Column(name = "AUTH_ID", nullable = false, unique = true)
    private String id;


    @Column(name = "USER_NAME", nullable = false) // 사용자 이름
    private String name;
	
    @Column(name = "USER_IMG", nullable = false) // 사용자 이미지
    private String img;

}
