package com.pry20220262.augmentedanatomy.service.References;

import com.pry20220262.augmentedanatomy.model.Reference;

import java.util.List;

public interface ReferenceService {
    Reference createReference(Long humanAnatomyId, Reference reference);
    List<Reference> getAllReferencesByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> getAllInternetReferenceByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> getAllOMSReferenceByHumanAnatomyId(Long humanAnatomyId);
    Reference updateReference(Long humanAnatomyId, Long referenceId, Reference referenceRequest);
}
