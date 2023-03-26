package com.pry20220262.augmentedanatomy.service.References;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService{

    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    @Override
    public Reference createReference(Long humanAnatomyId, Reference reference) {
        return humanAnatomyRepository.findById(humanAnatomyId).map(humanAnatomy -> {
            reference.setHumanAnatomy(humanAnatomy);
            return referenceRepository.save(reference);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }

    @Override
    public List<Reference> getAllReferencesByHumanAnatomyId(Long humanAnatomyId) {
        return referenceRepository.findByHumanAnatomyId(humanAnatomyId);
    }
}
