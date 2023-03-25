package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.QuizAttemptResource;
import com.pry20220262.augmentedanatomy.resource.QuizAttempt.SaveQuizAttemptResource;
import com.pry20220262.augmentedanatomy.service.QuizAttempt.QuizAttemptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    private QuizAttempt convertToEntity(SaveQuizAttemptResource resource){
        return mapper.map(resource, QuizAttempt.class);
    }

    private QuizAttemptResource convertToResource(QuizAttempt entity) {
        return mapper.map(entity, QuizAttemptResource.class);
    }

}
