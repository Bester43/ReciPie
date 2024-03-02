package com.oliverhalasz.recipie.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1024)
    private String description;

    private Integer cookingTimeInMinutes;
    private String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
