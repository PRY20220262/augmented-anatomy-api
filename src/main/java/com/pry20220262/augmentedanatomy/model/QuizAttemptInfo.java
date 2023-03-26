package com.pry20220262.augmentedanatomy.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizAttemptInfo {
    private Long id;
    private Double score;
    private Long humanAnatomyId;
    private String nameHumanAnatomy;
}
