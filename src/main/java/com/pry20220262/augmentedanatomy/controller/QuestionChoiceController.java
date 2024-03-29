package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import com.pry20220262.augmentedanatomy.repository.QuestionChoiceRepository;
import com.pry20220262.augmentedanatomy.resource.Note.NoteResource;
import com.pry20220262.augmentedanatomy.resource.Note.SaveNoteResource;
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
    @Autowired
    private QuestionChoiceRepository questionChoiceRepository;

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

    @PutMapping("/questions/{questionId}/questions-choice/{questionChoiceId}")
    public QuestionChoiceResource updateQuestionChoice(@PathVariable(name = "questionId") Long questionId, @PathVariable(name = "questionChoiceId") Long questionChoiceId, @Valid @RequestBody SaveQuestionChoiceResource resource) {
        return convertToResource(questionChoiceSerivce.updateQuestionChoiceByQuestionId(questionId, questionChoiceId, convertToEntity(resource)));
    }

    @GetMapping("quizAttempt/{quizAttemptId}/questions/{questionId}/questions-choice/{questionChoiceId}")
    public Boolean validateSelectedQuestionChoiceAndUpdateScore(@PathVariable(name = "questionId") Long questionId, @PathVariable(name = "questionChoiceId") Long questionChoiceId, @PathVariable(name = "quizAttemptId") Long quizAttemptId) {
       return questionChoiceSerivce.validateSelectedQuestionChoiceAndUpdateScore(questionId, questionChoiceId, quizAttemptId);
    }

    @GetMapping("questions/{questionId}/questions-choice/{questionChoiceId}")
    public Boolean validateRightQuestionChoice(@PathVariable(name = "questionId") Long questionId, @PathVariable(name = "questionChoiceId") Long questionChoiceId) {
        return questionChoiceSerivce.validateSelectedQuestionChoice(questionId, questionChoiceId);
    }

    private QuestionChoice convertToEntity(SaveQuestionChoiceResource resource){
        return mapper.map(resource, QuestionChoice.class);
    }

    private QuestionChoiceResource convertToResource(QuestionChoice entity){
        return mapper.map(entity, QuestionChoiceResource.class);
    }

}
