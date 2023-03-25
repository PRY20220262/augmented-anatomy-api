package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganSaveResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.SystemSaveResource;
import com.pry20220262.augmentedanatomy.service.HumanAnatomy.HumanAnatomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PostMapping("systems/{id}/organs")
    //TODO: ACTUALIZAR HUMAN BODY POR RESOURCE QUE SE REQUIERA EN FRONT (imagen, modelo, caracteristicas, etc)
    public HumanAnatomy createOrgan(@PathVariable(name = "id") Long id, @RequestBody OrganSaveResource resource) {
        return humanAnatomyService.createOrgan(resource);
    }

    //SISTEMAS
    @PostMapping("systems")
    //TODO: ACTUALIZAR HUMAN BODY POR RESOURCE QUE SE REQUIERA EN FRONT (imagen, modelo, caracteristicas, etc)
    public HumanAnatomy createSystem(@RequestBody SystemSaveResource resource) {
        return humanAnatomyService.createSystem(resource);
    }



}
