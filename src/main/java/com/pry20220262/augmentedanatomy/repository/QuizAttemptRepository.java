package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.QuizAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByUserId(Long userId);
    List<QuizAttempt> findByHumanAnatomyId(Long humanAnatomyId);

    Optional<QuizAttempt> findByIdAndUserId(Long id, Long userId);
}
