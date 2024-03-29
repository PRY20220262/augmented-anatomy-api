package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Question;
import com.pry20220262.augmentedanatomy.model.QuestionChoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
    Page<QuestionChoice> findByQuestionId(Long questionId, Pageable pageable);
    List<QuestionChoice> findByQuestionId(Long questionId);
    Optional<QuestionChoice> findByIdAndQuestionId(Long questionId, Long questionChoice);
}
