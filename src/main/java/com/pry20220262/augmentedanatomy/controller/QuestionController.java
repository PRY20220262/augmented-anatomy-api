package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.resource.Question.QuestionResource;
import com.pry20220262.augmentedanatomy.resource.Question.SaveQuestionResource;
import com.pry20220262.augmentedanatomy.service.Question.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("questions")
    public QuestionResource createProduct(@Valid @RequestBody SaveQuestionResource resource)  {
        Question question = convertToEntity(resource);
        return convertToResource(questionService.createQuestion(question));
    }



    private Question convertToEntity(SaveQuestionResource resource){
        return mapper.map(resource, Question.class);
    }

    private QuestionResource convertToResource(Question entity){
        return mapper.map(entity, QuestionResource.class);
    }

}
