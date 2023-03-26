package com.pry20220262.augmentedanatomy.service.References;


import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Reference;

import java.util.List;

public interface ReferenceService {
    Reference createReference(Long humanAnatomyId, Reference reference);
    List<Reference> getAllReferencesByHumanAnatomyId(Long humanAnatomyId);
}
