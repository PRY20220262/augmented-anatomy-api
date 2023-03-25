package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanAnatomyRepository extends JpaRepository<HumanAnatomy, Long> {
}
