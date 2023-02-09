package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.resource.NoteResource;
import com.pry20220262.augmentedanatomy.resource.SaveNoteResource;
import com.pry20220262.augmentedanatomy.service.Note.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<NoteResource> getAllCoursesByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        Page<Note> coursePage = noteService.getAllNotesByUserId(userId, pageable);
        List<NoteResource> resources = coursePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    
    private Note convertToEntity(SaveNoteResource resource){
        return mapper.map(resource, Note.class);
    }

    private NoteResource convertToResource(Note entity){
        return mapper.map(entity, NoteResource.class);
    }
}