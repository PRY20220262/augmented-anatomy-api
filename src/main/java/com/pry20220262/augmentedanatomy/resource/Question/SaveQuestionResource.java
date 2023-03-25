package com.pry20220262.augmentedanatomy.resource.Question;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveQuestionResource {
    @NotBlank
    @NotNull
    private String title;
}
