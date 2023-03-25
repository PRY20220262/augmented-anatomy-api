package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HumanAnatomyRepository extends JpaRepository<HumanAnatomy, Long> {
    String QUERY_FIND_ALL_NO_PARENT = "SELECT u FROM HumanAnatomy u WHERE u.parent = NULL ";
    @Query(value = QUERY_FIND_ALL_NO_PARENT)
    List<HumanAnatomy> findAllOrgans();
}
