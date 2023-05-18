package com.pry20220262.augmentedanatomy.resource.QuestionChoice;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuestionChoiceResource {
    private Long id;
    private String choice;
    private Boolean isCorrect;
}
