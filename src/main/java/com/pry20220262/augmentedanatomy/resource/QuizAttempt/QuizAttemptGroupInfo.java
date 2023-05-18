package com.pry20220262.augmentedanatomy.resource.QuizAttempt;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuizAttemptGroupInfo {
    private Double maxScore;
    private Long humanAnatomyId;
    private String nameHumanAnatomy;
    private int countAttempts;
    private List<QuizAttempt> quizAttemptByHumanAnatomy;
}
