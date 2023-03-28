package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptInfo;

import java.util.List;

public interface QuizAttemptService {
    QuizAttempt createQuizAttempt(Long userId, Long human_anatomy_id, QuizAttempt quizAttempt);
    List<QuizAttempt> getAllQuizAttemptByUserId(Long userId);
    List<QuizAttempt> getAllQuizAttemptByHumanAnatomyId(Long humanAnatomyId);
    QuizAttempt updateScoreQuizAttempt(Long quizAttemptId, Double score);
    List<QuizAttemptInfo> getAllQuizAttemptInfoByUserId(Long userId);

}
