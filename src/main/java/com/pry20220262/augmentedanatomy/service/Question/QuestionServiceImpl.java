package com.pry20220262.augmentedanatomy.service.Question;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    @Override
    public Question createQuestion(Long humanAnatomyId, Question question) {
        return humanAnatomyRepository.findById(humanAnatomyId).map(humanAnatomy -> {
            question.setHumanAnatomy(humanAnatomy);
            return questionRepository.save(question);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }

    @Override
    public List<Question> getAllQuestionsByHumanAnatomy(Long humanAnatomyId) {
        return questionRepository.findByHumanAnatomyId(humanAnatomyId);
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
}
