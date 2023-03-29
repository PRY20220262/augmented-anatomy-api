package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;

import lombok.Data;

@Data
public class SystemListResource {
    private Long id;

    private String name;

    private String shortDetail;

    private int organsNumber;

    private String image;
}
