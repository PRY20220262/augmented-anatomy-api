package com.pry20220262.augmentedanatomy.resource.Question;

import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDetail {
    private Long id;
    private String title;
    List<QuestionChoice> answers;
}