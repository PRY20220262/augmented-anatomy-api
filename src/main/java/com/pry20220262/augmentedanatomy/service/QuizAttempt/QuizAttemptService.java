package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.model.QuizAttempt;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptService {
    QuizAttempt createQuizAttempt(Long userId, Long human_anatomy_id, QuizAttempt quizAttempt);
    List<QuizAttempt> getAllQuizAttemptByUserId(Long userId);
    List<QuizAttempt> getAllQuizAttemptByHumanAnatomyId(Long humanAnatomyId);
}
