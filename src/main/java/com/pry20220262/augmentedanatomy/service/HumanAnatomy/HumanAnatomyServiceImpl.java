package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganSaveResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.SystemSaveResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanAnatomyServiceImpl implements HumanAnatomyService {

    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    private HumanAnatomy findById(Long id) {
        return humanAnatomyRepository.findById(id).orElseThrow(() -> new ServiceException(Error.ELEMENT_DOES_NOT_EXIST));
    }

    @Override
    public List<HumanAnatomy> findOrgans() {
        List<HumanAnatomy> organs = humanAnatomyRepository.findAllOrgans();
        if (organs.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);
        return organs;
    }

    @Override
    public HumanAnatomy createSystem(SystemSaveResource resource) {
        HumanAnatomy system = new HumanAnatomy();
        system.setName(resource.getName());
        system.setDetail(resource.getDetail());
        system.setShortDetail(resource.getShortDetail());
        system.setOrgansNumber(resource.getOrgansNumber());
        return humanAnatomyRepository.save(system);
    }

    @Override
    public HumanAnatomy createOrgan(OrganSaveResource resource) {
        HumanAnatomy organ = new HumanAnatomy();
        organ.setName(resource.getName());
        organ.setDetail(resource.getDetail());
        organ.setShortDetail(resource.getShortDetail());

        HumanAnatomy parent = findById(resource.getParentId());
        organ.setParent(parent);
        return humanAnatomyRepository.save(organ);    }
}
