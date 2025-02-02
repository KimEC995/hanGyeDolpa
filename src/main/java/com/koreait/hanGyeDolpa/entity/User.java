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
@Table(name = "USER") // 데이터베이스 테이블 이름과 일치
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO") // 데이터베이스의 기본 키 필드와 매핑
    private Long id;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private String username;

    @Column(name = "USER_PW", nullable = false) // 비밀번호
    private String password;

    @Column(name = "USER_ALIAS") // 닉네임 (null 가능)
    private String alias;

    @Column(name = "USER_NAME", nullable = false) // 사용자 이름
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Exercise> exerciseRecords; // 운동 기록 목록
}



