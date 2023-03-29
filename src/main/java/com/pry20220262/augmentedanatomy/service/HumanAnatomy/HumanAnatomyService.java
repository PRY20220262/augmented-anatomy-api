package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.*;

import java.util.List;

public interface HumanAnatomyService {

    List<OrganListResource> findOrgans();

    List<SystemListResource> findSystems();

    HumanAnatomyDetailResource getById(Long id);

    HumanAnatomy createSystem(SystemSaveResource systemSaveResource);

    HumanAnatomy createOrgan(OrganSaveResource organSaveResource);

}

