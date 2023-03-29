package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanAnatomyRepository extends JpaRepository<HumanAnatomy, Long> {
    String QUERY_FIND_ALL_ORGANS = "SELECT u FROM HumanAnatomy u WHERE u.parent != NULL ";
    String QUERY_FIND_ALL_SYSTEMS = "SELECT u FROM HumanAnatomy u WHERE u.parent = NULL ";
    @Query(value = QUERY_FIND_ALL_ORGANS)
    List<HumanAnatomy> findAllOrgans();

    @Query(value = QUERY_FIND_ALL_SYSTEMS)
    List<HumanAnatomy> findAllSystems();
}
