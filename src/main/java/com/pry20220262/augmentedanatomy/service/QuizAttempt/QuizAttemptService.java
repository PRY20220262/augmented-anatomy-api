package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;

import java.util.Optional;

public interface QuizAttemptService {
    QuizAttempt createQuizAttempt(Long userId, QuizAttempt quizAttempt);
}
