package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findByUserId(Long userId, Pageable pageable);
    //Optional<Note> finByIdAndUserId(Long id, Long userId);
    Optional<Note> findByIdAndUserId(Long id, Long userId);
}
