package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.resource.NoteResource;
import com.pry20220262.augmentedanatomy.resource.SaveNoteResource;
import com.pry20220262.augmentedanatomy.service.Note.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
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

    private Note convertToEntity(SaveNoteResource resource){
        return mapper.map(resource, Note.class);
    }

    private NoteResource convertToResource(Note entity){
        return mapper.map(entity, NoteResource.class);
    }
}
