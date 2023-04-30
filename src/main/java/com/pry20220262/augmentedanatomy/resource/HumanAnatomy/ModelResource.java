package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;

import lombok.Data;

@Data
public class ModelResource {
    private Long id;

    private String url;

    private String name;

    private String detail;

    private double x_scale;

    private double y_scale;

    private double z_scale;

    private double x_position;

    private double y_position;

    private double z_position;
}
