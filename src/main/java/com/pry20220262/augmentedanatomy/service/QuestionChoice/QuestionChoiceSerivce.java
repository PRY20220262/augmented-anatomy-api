package com.pry20220262.augmentedanatomy.service.QuestionChoice;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.QuestionChoice;

public interface QuestionChoiceSerivce {
    QuestionChoice createQuestionChoice(Long questionId, QuestionChoice questionChoice);
}
