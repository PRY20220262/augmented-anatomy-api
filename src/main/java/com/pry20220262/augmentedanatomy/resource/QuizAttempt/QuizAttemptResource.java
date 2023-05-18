package com.pry20220262.augmentedanatomy.resource.QuizAttempt;

import lombok.Data;

import java.util.Date;

@Data
public class QuizAttemptResource {
    private Long id;
    private Double score;
    private Date created_at;
}
