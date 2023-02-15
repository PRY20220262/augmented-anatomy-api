package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.UploadPhotoResource;
import com.pry20220262.augmentedanatomy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/{id}/upload-file")
    public ResponseEntity<String> uploadProfilePhoto(@PathVariable(name = "id") Long id, @RequestBody UploadPhotoResource uploadPhotoResource) {
        String resultService = userService.uploadProfilePhoto(uploadPhotoResource, id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
