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
@Table(name = "EXERCISE")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIMB_NO")
    private Long id;

    @Column(name = "CLIMB_DATE", nullable = false)
    private LocalDate exerciseDate;

    @Column(name = "CLIMB_PLACE", nullable = false)
    private String location;

    @Column(name = "CLIMB_STAGE", nullable = false)
    private int difficulty;

    @Column(name = "CLIMB_COUNT", nullable = false)
    private int count; // 운동 횟수

    @Column(name = "CLIMB_CALORIES", nullable = false)
    private int calories;

    @Column(name = "CLIMB_TIME", nullable = false)
    private int timeSpent; // 운동 시간 (분)

    @ManyToOne
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    // Constructor for easier instantiation
    public Exercise(String location, LocalDate exerciseDate, int difficulty, int count, int calories, int timeSpent, User user) {
        this.exerciseDate = exerciseDate;
        this.location = location;
        this.difficulty = difficulty;
        this.count = count; // 운동 횟수
        this.calories = calories;
        this.timeSpent = timeSpent;
        this.user = user; // 사용자 정보
    }
}




