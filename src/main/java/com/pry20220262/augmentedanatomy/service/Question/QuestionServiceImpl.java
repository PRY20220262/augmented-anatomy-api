package com.pry20220262.augmentedanatomy.service.Question;

import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
}
