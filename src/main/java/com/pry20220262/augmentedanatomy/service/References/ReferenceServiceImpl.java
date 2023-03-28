package com.pry20220262.augmentedanatomy.service.References;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.ReferenceRepository;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceType;
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

    @Override
    public List<Reference> getAllInternetReferenceByHumanAnatomyId(Long humanAnatomyId) {
        return referenceRepository.findByHumanAnatomyIdAndFuente(humanAnatomyId, ReferenceType.INTERNET);
    }

    @Override
    public List<Reference> getAllOMSReferenceByHumanAnatomyId(Long humanAnatomyId) {
        return referenceRepository.findByHumanAnatomyIdAndFuente(humanAnatomyId, ReferenceType.OMS);
    }

    @Override
    public Reference updateReference(Long humanAnatomyId, Long referenceId, Reference referenceRequest) {
        return humanAnatomyRepository.findById(humanAnatomyId).map(humanAnatomy -> {
            Reference reference = referenceRepository.findById(referenceId).orElseThrow(()-> new ServiceException(Error.DATA_NOT_FOUND));
            reference.setTitle(referenceRequest.getTitle());
            reference.setUrl(referenceRequest.getUrl());
            reference.setFuente(referenceRequest.getFuente());
            return referenceRepository.save(reference);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }
}
