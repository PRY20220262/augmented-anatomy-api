package com.pry20220262.augmentedanatomy.resource.QuestionChoice;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveQuestionChoiceResource {

    @NotBlank
    @NotNull
    private String choice;

    @NotNull
    private Boolean isCorrect;
}
