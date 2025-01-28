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
@Table(name = "EXERCISE_TABLE")
public class Exercise_Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "USER_NO")
    private Long uid;
    
    @Column(name = "CLIMB_NO")
    private Long id;

    @Column(name = "CLIMB_DATE", nullable = false)
    private LocalDate exerciseDate;

    @Column(name = "CLIMB_PLACE", nullable = false)
    private String location;

    @Column(name = "CLIMB_STAGE", nullable = false)
    private int difficulty;

    @Column(name = "CLIMB_COUNT", nullable = false)
    private int count;

    @Column(name = "CLIMB_CALORIES", nullable = false)
    private int calories;

    @Column(name = "CLIMB_TIME", nullable = false)
    private int timeSpent;

    // Constructor for easier instantiation
    public Exercise_Table(LocalDate exerciseDate, String location, int difficulty, int count, int calories, int timeSpent) {
        this.exerciseDate = exerciseDate;
        this.location = location;
        this.difficulty = difficulty;
        this.count = count;
        this.calories = calories;
        this.timeSpent = timeSpent;
    }
}
