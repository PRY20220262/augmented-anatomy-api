package com.pry20220262.augmentedanatomy.service.QuestionChoice;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import com.pry20220262.augmentedanatomy.repository.QuestionChoiceRepository;
import com.pry20220262.augmentedanatomy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionChoiceImpl implements QuestionChoiceSerivce{

    @Autowired
    private QuestionChoiceRepository questionChoiceRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionChoice createQuestionChoice(Long questionId, QuestionChoice questionChoice) {
        return questionRepository.findById(questionId).map(question -> {
            questionChoice.setQuestion(question);
            return questionChoiceRepository.save(questionChoice);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }

    @Override
    public Page<QuestionChoice> getAllQuestionsChoicebyQuestionId(Long questionId, Pageable pageable) {
        return questionChoiceRepository.findByQuestionId(questionId, pageable);
    }

    @Override
    public QuestionChoice updateQuestionChoiceByQuestionId(Long questionId, Long questionChoiceId, QuestionChoice questionChoiceRequest) {
        return questionRepository.findById(questionId).map(question -> {
            QuestionChoice questionChoice = questionChoiceRepository.findById(questionChoiceId).orElseThrow(()-> new ServiceException(Error.DATA_NOT_FOUND));
            questionChoice.setChoice(questionChoiceRequest.getChoice());
            questionChoice.setIsCorrect(questionChoiceRequest.getIsCorrect());
            questionChoice.setQuestion(question);
            return questionChoiceRepository.save(questionChoice);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }
}
