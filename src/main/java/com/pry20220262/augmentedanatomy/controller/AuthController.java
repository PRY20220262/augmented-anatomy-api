package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.exception.RestExceptionHandler;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.UserSaveResource;
import com.pry20220262.augmentedanatomy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserSaveResource userSaveResource) {
        logger.info("Realizando post /register");
        return userService.register(userSaveResource);

    }
}
