package com.pry20220262.augmentedanatomy.service.Question;

import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.resource.Question.QuestionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Long humanAnatomyId, Question question);
    List<Question> getAllQuestionsByHumanAnatomy(Long humanAnatomyId);
    List<Question> getRandomQuestionsByHumanAnatomyId(Long humanAnatomyId);
    List<QuestionDetail> getRandomQuestionsAndAnswersByHumanAnatomyId(Long humanAnatomyId);
    Page<Question> getAllQuestions(Pageable pageable);
}
