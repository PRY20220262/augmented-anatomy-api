package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;

import lombok.Data;


@Data
public class OrganResource {
    private Long id;

    private String name;

    private String shortDetail;

    private String image;

    private String system;
}
