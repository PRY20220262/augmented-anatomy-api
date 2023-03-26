package com.pry20220262.augmentedanatomy.resource.HumanAnatomy;


import com.pry20220262.augmentedanatomy.model.Characteristic;
import lombok.Data;

import java.util.List;

@Data
public class HumanAnatomyDetailResource {
    private Long id;

    private String name;

    private String detail;

    private String image;

    private List<Characteristic> characteristics;

    // TODO: AGREGAR LISTA DE REFERENCIAS
}
