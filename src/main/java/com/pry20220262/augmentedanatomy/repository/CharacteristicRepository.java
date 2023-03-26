package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    List<Characteristic> findByHumanAnatomyId(Long humanAnatomyId);

}
