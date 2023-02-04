package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }
}
