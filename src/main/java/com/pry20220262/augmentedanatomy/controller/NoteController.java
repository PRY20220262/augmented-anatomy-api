package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.resource.Note.NoteResource;
import com.pry20220262.augmentedanatomy.resource.Note.SaveNoteResource;
import com.pry20220262.augmentedanatomy.service.Note.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    ModelMapper mapper;
    @PostMapping("/users/{userId}/notes")
    public NoteResource createNote(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveNoteResource resource) {
        return convertToResource(noteService.createNote(userId, convertToEntity(resource)));
    }

    @GetMapping("/users/{userId}/notes")
    public List<NoteResource> getAllNotesByUserId(@PathVariable(name = "userId") Long userId) {
        List<Note> coursePage = noteService.getAllNotesByUserId(userId);
        return coursePage.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PutMapping("/users/{userId}/notes/{noteId}")
    public NoteResource updateNote(@PathVariable(name = "userId") Long userId, @PathVariable(name = "noteId") Long noteId, @Valid @RequestBody SaveNoteResource resource) {
        return convertToResource(noteService.updateNote(userId, noteId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable(name = "userId") Long userId, @PathVariable(name = "noteId") Long noteId) {
        return noteService.deleteNote(userId, noteId);
    }

    private Note convertToEntity(SaveNoteResource resource){
        return mapper.map(resource, Note.class);
    }

    private NoteResource convertToResource(Note entity){
        return mapper.map(entity, NoteResource.class);
    }
}
