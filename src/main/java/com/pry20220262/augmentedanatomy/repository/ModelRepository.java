package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<List<Model>> findByHumanAnatomyId(Long humanAnatomyId);
}
