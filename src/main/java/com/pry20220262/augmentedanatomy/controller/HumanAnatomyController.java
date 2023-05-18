package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.*;
import com.pry20220262.augmentedanatomy.resource.Menu.MenuResource;
import com.pry20220262.augmentedanatomy.service.HumanAnatomy.HumanAnatomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class HumanAnatomyController {

    @Autowired
    HumanAnatomyService humanAnatomyService;

    //SE PUEDE REUTILIZAR PARA ORGANOS Y SISTEMAS
    //getSystemById
    //getOrganById
    @GetMapping("/human-anatomy/{id}")
    public HumanAnatomyDetailResource getById(@PathVariable(name = "id") Long id) {
        return humanAnatomyService.getById(id);
    }

    @GetMapping("/organs")
    public List<OrganResource> organQuery(OrganQuery query) {
        return humanAnatomyService.organQuery(query);
    }


    @GetMapping("systems")
    public List<SystemResource> findAllSystems(SystemQuery query) {
        return humanAnatomyService.systemQuery(query);
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

    @GetMapping("/users/{email}/main-menu")
    public MenuResource getMainMenu(@PathVariable(name = "email") String email) {
        return humanAnatomyService.mainMenu(email);
    }


}
