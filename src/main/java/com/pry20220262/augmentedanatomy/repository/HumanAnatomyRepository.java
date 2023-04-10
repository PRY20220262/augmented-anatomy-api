package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanAnatomyRepository extends JpaRepository<HumanAnatomy, Long> {

    String QUERY_FIND_ALL_SYSTEMS = "SELECT u FROM HumanAnatomy u WHERE u.parent = NULL ";

    @Query(value = QUERY_FIND_ALL_SYSTEMS)
    List<HumanAnatomy> findAllSystems();

    @Query(value = "SELECT u " +
            " FROM HumanAnatomy u" +
            " WHERE u.parent != NULL " +
            " AND (u.id = :id OR :id IS NULL) " +
            " AND (u.name LIKE CONCAT('%', :name, '%') OR :name IS NULL) " +
            " AND (u.parent.name = :systemName OR :systemName IS NULL) ")
    List<HumanAnatomy> queryOrgan(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("systemName") String systemName
    );
}
