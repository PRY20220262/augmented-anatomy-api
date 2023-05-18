package com.pry20220262.augmentedanatomy.service.Note;
import com.pry20220262.augmentedanatomy.model.Note;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface NoteService {
    Note createNote(Long userId, Note note);
    Note getNoteById(Long noteId);
    List<Note> getAllNotesByUserId(Long userId);
    Note updateNote(Long userId, Long noteId, Note noteRequest);
    ResponseEntity<?> deleteNote(Long userId, Long noteId);
}
