package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.*;
import com.pry20220262.augmentedanatomy.resource.Menu.MenuResource;

import java.util.List;

public interface HumanAnatomyService {

    List<OrganResource> organQuery(OrganQuery query);

    List<SystemResource> systemQuery(SystemQuery query);

    HumanAnatomyDetailResource getById(Long id);

    HumanAnatomy createSystem(SystemSaveResource systemSaveResource);

    HumanAnatomy createOrgan(OrganSaveResource organSaveResource);

    MenuResource mainMenu(String email);


}

