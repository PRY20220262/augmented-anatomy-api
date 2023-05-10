package com.pry20220262.augmentedanatomy.service.QuizAttempt;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.*;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.QuizAttemptRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptGroupInfo;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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
                LocalDateTime currentDateTime = LocalDateTime.now();
                Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
                quizAttempt.setCreated_at(currentDate);
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

    @Override
    public List<QuizAttemptInfo> getAllQuizAttemptInfoByUserId(Long userId) {
        List<QuizAttempt> quizAttemptsList = quizAttemptRepository.findByUserId(userId);
        List<QuizAttemptInfo> quizAttemptInfoList = new ArrayList<>();
        for (QuizAttempt quizAttempt: quizAttemptsList){
            QuizAttemptInfo quizAttemptInfo = new QuizAttemptInfo();
            quizAttemptInfo.setId(quizAttempt.getId());
            quizAttemptInfo.setScore(quizAttempt.getScore());
            quizAttemptInfo.setCreated_at(quizAttempt.getCreated_at());
            quizAttemptInfo.setHumanAnatomyId(quizAttempt.getHumanAnatomy().getId());
            quizAttemptInfo.setNameHumanAnatomy(quizAttempt.getHumanAnatomy().getName());
            quizAttemptInfoList.add(quizAttemptInfo);
        }
        return quizAttemptInfoList;
    }

    @Override
    public List<QuizAttemptGroupInfo> getAllQuizAttemptGroupsByUserId(Long userId) {
        List<QuizAttempt> quizAttempts = quizAttemptRepository.findByUserId(userId);
        Map<Long, List<QuizAttempt>> groupedQuizAttempts = quizAttempts.stream()
                .collect(Collectors.groupingBy(qa -> qa.getHumanAnatomy().getId()));

        List<QuizAttemptGroupInfo> quizAttemptGroups = new ArrayList<>();
        for (Map.Entry<Long, List<QuizAttempt>> entry : groupedQuizAttempts.entrySet()) {
            Long humanAnatomyId = entry.getKey();
            List<QuizAttempt> quizAttemptByHumanAnatomy = entry.getValue();
            Double maxScore = quizAttemptByHumanAnatomy.stream()
                    .mapToDouble(QuizAttempt::getScore)
                    .max()
                    .orElse(0.0);
            String nameHumanAnatomy = quizAttemptByHumanAnatomy.get(0).getHumanAnatomy().getName();
            int countAttempts = quizAttemptByHumanAnatomy.size();

            QuizAttemptGroupInfo quizAttemptGroupInfo = new QuizAttemptGroupInfo();
            quizAttemptGroupInfo.setHumanAnatomyId(humanAnatomyId);
            quizAttemptGroupInfo.setNameHumanAnatomy(nameHumanAnatomy);
            quizAttemptGroupInfo.setMaxScore(maxScore);
            quizAttemptGroupInfo.setCountAttempts(countAttempts);
            quizAttemptGroupInfo.setQuizAttemptByHumanAnatomy(quizAttemptByHumanAnatomy);
            quizAttemptGroups.add(quizAttemptGroupInfo);
        }
        return quizAttemptGroups;
    }
}
