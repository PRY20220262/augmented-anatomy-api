package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Note;
import com.pry20220262.augmentedanatomy.model.QuizAttemptInfo;
import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.resource.Note.NoteResource;
import com.pry20220262.augmentedanatomy.resource.Note.SaveNoteResource;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceResource;
import com.pry20220262.augmentedanatomy.resource.Reference.SaveReferenceResource;
import com.pry20220262.augmentedanatomy.service.Note.NoteService;
import com.pry20220262.augmentedanatomy.service.References.ReferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReferenceController {
    @Autowired
    private ReferenceService referenceService;

    @Autowired
    ModelMapper mapper;

    @PostMapping("humanAnatomy/{humanAnatomyId}/references")
    public ReferenceResource createReference(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId, @Valid @RequestBody SaveReferenceResource resource) {
        return convertToResource(referenceService.createReference(humanAnatomyId, convertToEntity(resource)));
    }

    @GetMapping("humanAnatomy/{humanAnatomyId}/references")
    public List<Reference> getAllReferences(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return referenceService.getAllReferencesByHumanAnatomyId(humanAnatomyId);
    }

    private Reference convertToEntity(SaveReferenceResource resource){
        return mapper.map(resource, Reference.class);
    }

    private ReferenceResource convertToResource(Reference entity){
        return mapper.map(entity, ReferenceResource.class);
    }
}
