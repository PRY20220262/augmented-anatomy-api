package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrganSaveResource {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    private String name;


    @NotNull(message = "Short detail cannot be null")
    @NotBlank(message = "Shot detail cannot be null")
    private String shortDetail;

    @NotNull(message = "Detail cannot be null")
    @NotBlank(message = "Detail cannot be null")
    private String detail;

    private boolean hasGender;

    @NotNull(message = "Parent cannot be null")
    private Long parentId;

}
