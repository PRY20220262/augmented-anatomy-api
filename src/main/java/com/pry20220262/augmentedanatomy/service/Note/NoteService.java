package com.pry20220262.augmentedanatomy.service.Note;

import com.pry20220262.augmentedanatomy.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NoteService {
    Note createNote(Long userId, Note note);
    Note getNoteById(Long noteId);
    Page<Note> getAllNotesByUserId(Long userId, Pageable pageable);
    Note updateNote(Long noteId, Note note);
    ResponseEntity<?> deleteNote(Long noteId);
}