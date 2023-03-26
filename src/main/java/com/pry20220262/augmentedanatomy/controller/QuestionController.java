package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.resource.Note.NoteResource;
import com.pry20220262.augmentedanatomy.resource.Note.SaveNoteResource;
import com.pry20220262.augmentedanatomy.resource.Question.QuestionResource;
import com.pry20220262.augmentedanatomy.resource.Question.SaveQuestionResource;
import com.pry20220262.augmentedanatomy.service.Question.QuestionService;
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
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("humanAnatomy/{humanAnatomyId}/questions")
    public QuestionResource createQuestion(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId, @Valid @RequestBody SaveQuestionResource resource) {
        return convertToResource(questionService.createQuestion(humanAnatomyId, convertToEntity(resource)));
    }

    @GetMapping("questions")
    public Page<QuestionResource> getAllQuestions(Pageable pageable) {
        Page<Question> customerPage = questionService.getAllQuestions(pageable);
        List<QuestionResource> resources = customerPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("humanAnatomy/{humanAnatomyId}/questions")
    public List<Question> getAllQuestionsByHumanAnatomyId(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return questionService.getAllQuestionsByHumanAnatomy(humanAnatomyId);
    }

    @GetMapping("humanAnatomy/{humanAnatomyId}/randomsQuestions")
    public List<Question> getRandomQuestionsByHumanAnatomyId(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return questionService.getRandomQuestionsByHumanAnatomyId(humanAnatomyId);
    }

    private Question convertToEntity(SaveQuestionResource resource){
        return mapper.map(resource, Question.class);
    }

    private QuestionResource convertToResource(Question entity){
        return mapper.map(entity, QuestionResource.class);
    }

}
