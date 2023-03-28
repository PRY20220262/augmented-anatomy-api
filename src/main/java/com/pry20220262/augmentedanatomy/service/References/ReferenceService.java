package com.pry20220262.augmentedanatomy.service.References;

import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceType;

import java.util.List;

public interface ReferenceService {
    Reference createReference(Long humanAnatomyId, Reference reference);
    List<Reference> getAllReferencesByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> getAllInternetReferenceByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> getAllOMSReferenceByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> getAllReferenceByHumanAnatomyIdAndReferenceType(Long humanAnatomyId, ReferenceType referenceType);
    Reference updateReference(Long humanAnatomyId, Long referenceId, Reference referenceRequest);
}
