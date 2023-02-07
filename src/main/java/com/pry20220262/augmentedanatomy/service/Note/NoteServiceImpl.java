package com.pry20220262.augmentedanatomy.service.Note;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService
{
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return null;
    }

    @Override
    public Page<Note> getAllNotes(Pageable pageable) {
        return null;
    }

    @Override
    public Note updateNote(Long noteId, Note note) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteNote(Long noteId) {
        return null;
    }
}
