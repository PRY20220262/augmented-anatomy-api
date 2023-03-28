package com.pry20220262.augmentedanatomy.resource.QuizAttempt;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SaveQuizAttemptResource {
    private Double score;
    private Date created_at;
}
