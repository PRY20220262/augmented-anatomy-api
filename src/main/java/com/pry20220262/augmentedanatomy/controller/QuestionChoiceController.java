package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import com.pry20220262.augmentedanatomy.resource.QuestionChoice.QuestionChoiceResource;
import com.pry20220262.augmentedanatomy.resource.QuestionChoice.SaveQuestionChoiceResource;
import com.pry20220262.augmentedanatomy.service.QuestionChoice.QuestionChoiceSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuestionChoiceController {

    @Autowired
    private QuestionChoiceSerivce questionChoiceSerivce;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/questions/{questionId}/questions-choice")
    public QuestionChoiceResource createQuestionChoice(@PathVariable(name = "questionId") Long questionId, @Valid @RequestBody SaveQuestionChoiceResource resource) {
        return convertToResource(questionChoiceSerivce.createQuestionChoice(questionId, convertToEntity(resource)));
    }

    private QuestionChoice convertToEntity(SaveQuestionChoiceResource resource){
        return mapper.map(resource, QuestionChoice.class);
    }

    private QuestionChoiceResource convertToResource(QuestionChoice entity){
        return mapper.map(entity, QuestionChoiceResource.class);
    }

}
