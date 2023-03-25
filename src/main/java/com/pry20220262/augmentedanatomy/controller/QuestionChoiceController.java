package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import com.pry20220262.augmentedanatomy.resource.QuestionChoice.QuestionChoiceResource;
import com.pry20220262.augmentedanatomy.resource.QuestionChoice.SaveQuestionChoiceResource;
import com.pry20220262.augmentedanatomy.service.QuestionChoice.QuestionChoiceSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/questions/{questionId}/questions-choice")
    public Page<QuestionChoiceResource> getAllQuestionChoiceByQuestionId(@PathVariable(name = "questionId") Long questionId, Pageable pageable) {
        Page<QuestionChoice> questionChoiceePage = questionChoiceSerivce.getAllQuestionsChoicebyQuestionId(questionId, pageable);
        List<QuestionChoiceResource> resources = questionChoiceePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private QuestionChoice convertToEntity(SaveQuestionChoiceResource resource){
        return mapper.map(resource, QuestionChoice.class);
    }

    private QuestionChoiceResource convertToResource(QuestionChoice entity){
        return mapper.map(entity, QuestionChoiceResource.class);
    }

}
