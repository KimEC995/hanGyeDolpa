package com.koreait.hanGyeDolpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String exerciseType; // 운동 종류

    @Column(nullable = false)
    private LocalDate exerciseDate; // 운동 날짜

    @Column(nullable = false)
    private String location; // 장소

    @Column(nullable = false)
    private int difficulty; // 난이도 (1~10)

    @Column(nullable = false)
    private int calories; // 소모 칼로리

    @Column(nullable = false)
    private int timeSpent; // 운동 시간 (분)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // nullable을 true로 변경
    private User user; // 사용자 정보와 연결

    // Constructor for easier instantiation
    public ExerciseRecord(String exerciseType, LocalDate exerciseDate, String location, int difficulty, int calories, int timeSpent, User user) {
        this.exerciseType = exerciseType;
        this.exerciseDate = exerciseDate;
        this.location = location;
        this.difficulty = difficulty;
        this.calories = calories;
        this.timeSpent = timeSpent;
        this.user = user; // 이 부분은 null이 허용됨
    }
}


