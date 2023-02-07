package com.pry20220262.augmentedanatomy.resource;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveNoteResource {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    private String detail;
}
