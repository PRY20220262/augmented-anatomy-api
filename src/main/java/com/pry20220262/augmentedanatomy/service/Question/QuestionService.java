package com.pry20220262.augmentedanatomy.service.Question;

import com.pry20220262.augmentedanatomy.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Question createQuestion(Question question);
    Page<Question> getAllQuestions(Pageable pageable);
}
