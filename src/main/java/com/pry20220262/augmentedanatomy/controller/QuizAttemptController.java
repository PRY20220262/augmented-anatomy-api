package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptInfo;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptResource;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.SaveQuizAttemptResource;
import com.pry20220262.augmentedanatomy.service.QuizAttempt.QuizAttemptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/users/{userId}/humanAnatomy/{humanAnatomyId}/quizAttempt")
    public QuizAttemptResource createQuizAttempt(@PathVariable(name = "userId") Long userId, @PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt = quizAttemptService.createQuizAttempt(userId, humanAnatomyId, quizAttempt);
        return convertToResource(quizAttempt);
    }

    @GetMapping("/humanAnatomy/{humanAnatomyId}/quizAttempts")
    public List<QuizAttempt> getAllQuizAttemptByHumanAnatomyId(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return quizAttemptService.getAllQuizAttemptByHumanAnatomyId(humanAnatomyId);
    }

    @GetMapping("/users/{userId}/quizAttempts")
    public List<QuizAttempt> getAllQuizAttemptByUserId(@PathVariable(name = "userId") Long userId) {
        return quizAttemptService.getAllQuizAttemptByUserId(userId);
    }

    @GetMapping("users/{userId}/quizAttemptsInfo")
    public List<QuizAttemptInfo> getAllQuizInfoByUserId(@PathVariable(name = "userId") Long userId) {
        return quizAttemptService.getAllQuizAttemptInfoByUserId(userId);
    }

    @PutMapping("quizAttempts/{quizAttemptId}/score/{score}")
    public QuizAttemptResource updateQuizAttemptScore(@PathVariable(name = "quizAttemptId") Long userId, @PathVariable(name = "score") Double score) {
        return convertToResource(quizAttemptService.updateScoreQuizAttempt(userId, score));
    }

    private QuizAttempt convertToEntity(SaveQuizAttemptResource resource){
        return mapper.map(resource, QuizAttempt.class);
    }

    private QuizAttemptResource convertToResource(QuizAttempt entity) {
        return mapper.map(entity, QuizAttemptResource.class);
    }

}
