package com.pry20220262.augmentedanatomy.controller;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.InvalidDataException;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.UserSaveResource;
import com.pry20220262.augmentedanatomy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserSaveResource userSaveResource, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result, Error.INVALID_DATA);
        }
        User user = new User();
        user.setPassword(userSaveResource.getPassword());
        user.setEmail(userSaveResource.getEmail());
        return userService.register(user);

    }
}
