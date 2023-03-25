package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.QuizAttemptRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService{

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;
    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public QuizAttempt createQuizAttempt(Long userId, Long human_anatomy_id, QuizAttempt quizAttempt) {
        return userRepository.findById(userId).map(user -> {
            return humanAnatomyRepository.findById(human_anatomy_id).map( humanAnatomy -> {
                quizAttempt.setUser(user);
                quizAttempt.setHumanAnatomy(humanAnatomy);
                quizAttempt.setScore(0.00);
                return quizAttemptRepository.save(quizAttempt);
            }).orElseThrow(() ->  new ServiceException(Error.DATA_NOT_FOUND));
        }).orElseThrow(() ->  new ServiceException(Error.DATA_NOT_FOUND));
    }

    @Override
    public List<QuizAttempt> getAllQuizAttemptByUserId(Long userId) {
        return quizAttemptRepository.findByUserId(userId);
    }

    @Override
    public List<QuizAttempt> getAllQuizAttemptByHumanAnatomyId(Long humanAnatomyId) {
        return quizAttemptRepository.findByHumanAnatomyId(humanAnatomyId);
    }

    @Override
    public QuizAttempt updateScoreQuizAttempt(Long quizAttemptId, Double score) {
        return quizAttemptRepository.findById(quizAttemptId).map(quizAttempt -> {
            quizAttempt.setScore(score);
            return quizAttemptRepository.save(quizAttempt);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }
}
