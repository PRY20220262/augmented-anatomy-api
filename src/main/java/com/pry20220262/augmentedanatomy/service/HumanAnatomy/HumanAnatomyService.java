package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganSaveResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganListResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.SystemSaveResource;

import java.util.List;

public interface HumanAnatomyService {

    List<OrganListResource> findOrgans();

    HumanAnatomy createSystem(SystemSaveResource systemSaveResource);

    HumanAnatomy createOrgan(OrganSaveResource organSaveResource);

}

