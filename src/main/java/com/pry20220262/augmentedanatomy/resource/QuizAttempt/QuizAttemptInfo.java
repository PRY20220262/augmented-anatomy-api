package com.pry20220262.augmentedanatomy.resource.QuizAttempt;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QuizAttemptInfo {
    private Long id;
    private Double score;
    private Date created_at;
    private Long humanAnatomyId;
    private String nameHumanAnatomy;
}
