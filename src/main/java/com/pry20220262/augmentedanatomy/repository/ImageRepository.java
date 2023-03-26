package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Image;
import com.pry20220262.augmentedanatomy.resource.Image.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    String QUERY_GET_SHORT_IMAGE = "SELECT u FROM Image u WHERE u.humanAnatomy.id = :id AND " +
            "u.type = :type";
    @Query(value = QUERY_GET_SHORT_IMAGE)
    Optional<Image> getByTypeAndHumanAnatomyId(@Param("type") ImageType type, @Param("id") Long id);

}
