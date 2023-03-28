package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.Reference;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceResource;
import com.pry20220262.augmentedanatomy.resource.Reference.ReferenceType;
import com.pry20220262.augmentedanatomy.resource.Reference.SaveReferenceResource;
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

    @GetMapping("humanAnatomy/{humanAnatomyId}/internetReferences")
    public List<Reference> getAllInternetReferences(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return referenceService.getAllInternetReferenceByHumanAnatomyId(humanAnatomyId);
    }

    @GetMapping("humanAnatomy/{humanAnatomyId}/OMSReferences")
    public List<Reference> getAllOMSReferences(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId) {
        return referenceService.getAllOMSReferenceByHumanAnatomyId(humanAnatomyId);
    }

    @GetMapping("humanAnatomy/{humanAnatomyId}/reference")
    public List<Reference> getAllReferencesType(@PathVariable(name = "humanAnatomyId") Long humanAnatomyId, @RequestParam(name = "referenceType") ReferenceType referenceType) {
        return referenceService.getAllReferenceByHumanAnatomyIdAndReferenceType(humanAnatomyId, referenceType);
    }

    private Reference convertToEntity(SaveReferenceResource resource){
        return mapper.map(resource, Reference.class);
    }

    private ReferenceResource convertToResource(Reference entity){
        return mapper.map(entity, ReferenceResource.class);
    }
}
