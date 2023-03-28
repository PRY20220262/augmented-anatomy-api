package com.pry20220262.augmentedanatomy.resource.Reference;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveReferenceResource {
    @NotNull
    private String url;
    @NotBlank
    @NotNull
    @Size(max = 30)
    private String title;
    @NotBlank
    @NotNull
    @Size(max = 30)
    private String fuente;
}
