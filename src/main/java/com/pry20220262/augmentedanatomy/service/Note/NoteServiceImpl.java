package com.pry20220262.augmentedanatomy.service.Note;


import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.repository.NoteRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pry20220262.augmentedanatomy.model.Note;

@Service
public class NoteServiceImpl implements NoteService
{
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Note createNote(Long userId, Note note) {
        return userRepository.findById(userId).map(user -> {
            note.setUser(user);
            return noteRepository.save(note);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));

    }

    @Override
    public Note getNoteById(Long noteId) {
        return null;
    }

    @Override
    public Page<Note> getAllNotesByUserId(Long userId, Pageable pageable) {
        return noteRepository.findByUserId(userId, pageable);
    }

    @Override
    public Note updateNote(Long userId, Long noteId, Note noteRequest){
        return userRepository.findById(userId).map(user -> {
            Note note = noteRepository.findById(noteId).orElseThrow(()-> new ServiceException(Error.DATA_NOT_FOUND));
            note.setTitle(noteRequest.getTitle());
            note.setDetail(noteRequest.getDetail());
            return noteRepository.save(note);
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> deleteNote(Long userId, Long noteId) {
        return noteRepository.findByIdAndUserId(noteId, userId).map(note ->{
            noteRepository.delete(note);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
    }
}
