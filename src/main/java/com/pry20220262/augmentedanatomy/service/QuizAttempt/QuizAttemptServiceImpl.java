package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.repository.QuizAttemptRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService{

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public QuizAttempt createQuizAttempt(Long userId, Long human_anatomy_id, QuizAttempt quizAttempt) {
        return userRepository.findById(userId).map(user -> {
            quizAttempt.setUser(user);
            /*quizAttempt.setHumanAnatomy()*/
            System.out.println(human_anatomy_id);
            quizAttempt.setScore(0.00);
            return quizAttemptRepository.save(quizAttempt);
        }).orElseThrow(() ->  new ServiceException(Error.DATA_NOT_FOUND));
    }
}
