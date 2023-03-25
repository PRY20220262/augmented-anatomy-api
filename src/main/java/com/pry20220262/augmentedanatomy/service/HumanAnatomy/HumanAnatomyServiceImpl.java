package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanAnatomyServiceImpl implements HumanAnatomyService {

    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    @Override
    public List<HumanAnatomy> findOrgans() {
        List<HumanAnatomy> organs = humanAnatomyRepository.findAllOrgans();
        if (organs.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);
        return organs;
    }
}
