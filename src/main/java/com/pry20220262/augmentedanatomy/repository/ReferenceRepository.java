package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    List<Reference> findByHumanAnatomyId(Long humanAnatomyId);
    List<Reference> findByHumanAnatomyIdAndFuente(Long humanAnatomyId, ReferenceType fuente);
}
