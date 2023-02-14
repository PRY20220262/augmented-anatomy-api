package com.pry20220262.augmentedanatomy.service.QuestionChoice;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionChoiceSerivce {
    QuestionChoice createQuestionChoice(Long questionId, QuestionChoice questionChoice);
    Page<QuestionChoice> getAllQuestionsChoicebyQuestionId(Long questionId, Pageable pageable);
}
