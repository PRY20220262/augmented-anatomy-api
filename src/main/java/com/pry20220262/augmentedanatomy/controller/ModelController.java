package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Model;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.ModelResource;
import com.pry20220262.augmentedanatomy.service.HumanAnatomy.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ModelController {

    @Autowired
    ModelService modelService;

    @Autowired
    ModelMapper mapper;

    @GetMapping("human-anatomy/{id}/models")
    public List<ModelResource> findModelsByHumanAnatomyId(@PathVariable(name = "id") Long id) {
        return modelService.findModels(id).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    private ModelResource convertToResource(Model entity){
        return mapper.map(entity, ModelResource.class);
    }

}
