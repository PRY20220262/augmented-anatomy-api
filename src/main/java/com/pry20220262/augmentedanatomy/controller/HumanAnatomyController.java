package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.service.HumanAnatomy.HumanAnatomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HumanAnatomyController {

    @Autowired
    HumanAnatomyService humanAnatomyService;

    //ORGANOS
    @GetMapping("/organs")
    //TODO: ACTUALIZAR HUMAN BODY POR RESOURCE QUE SE REQUIERA EN FRONT (imagen, modelo, caracteristicas, etc)
    public List<HumanAnatomy> findAllOrgans() {
        return humanAnatomyService.findOrgans();
    }




}
