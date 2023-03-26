package com.pry20220262.augmentedanatomy.service.QuestionChoice;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.repository.QuestionChoiceRepository;
import com.pry20220262.augmentedanatomy.repository.QuestionRepository;
import com.pry20220262.augmentedanatomy.repository.QuizAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionChoiceImpl implements QuestionChoiceSerivce{

    @Autowired
    private QuestionChoiceRepository questionChoiceRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

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

    @Override
    public Boolean validateSelectedQuestionChoice(Long questionId, Long questionChoiceId) {
        List<QuestionChoice> allQuestionsChoice =  questionChoiceRepository.findByQuestionId(questionId);
        Optional<QuestionChoice> questionChoice = questionChoiceRepository.findByIdAndQuestionId(questionChoiceId, questionId);
        QuestionChoice rightChoice = new QuestionChoice();
        for (QuestionChoice choice : allQuestionsChoice) {
            if (choice.getIsCorrect()) {
                rightChoice = choice;
            }
        }
        if(rightChoice.getIsCorrect() == questionChoice.get().getIsCorrect()){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean validateSelectedQuestionChoiceAndUpdateScore(Long questionId, Long questionChoiceId, Long quizAttempt) {
        List<QuestionChoice> allQuestionsChoice =  questionChoiceRepository.findByQuestionId(questionId);
        Optional<QuestionChoice> questionChoice = questionChoiceRepository.findByIdAndQuestionId(questionChoiceId, questionId);
        QuestionChoice rightChoice = new QuestionChoice();
        for (QuestionChoice choice : allQuestionsChoice) {
            if (choice.getIsCorrect()) {
                rightChoice = choice;
            }
        }
        if(rightChoice.getIsCorrect() == questionChoice.get().getIsCorrect()){
            Optional<QuizAttempt> quizAttemptUpdated = quizAttemptRepository.findById(quizAttempt);
            if(quizAttemptUpdated.get().getHumanAnatomy().getId() == rightChoice.getQuestion().getHumanAnatomy().getId()){
                Double newScore = quizAttemptUpdated.get().getScore() + 5.0;
                quizAttemptUpdated.get().setScore(newScore);
                quizAttemptRepository.save(quizAttemptUpdated.get());
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
