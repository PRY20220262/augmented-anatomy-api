package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
