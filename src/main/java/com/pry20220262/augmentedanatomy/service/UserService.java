package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.ChangePasswordResource;
import org.springframework.http.ResponseEntity;
import com.pry20220262.augmentedanatomy.resource.User.UserSaveResource;

public interface UserService {
    User findById(Long id);
    User register(UserSaveResource user);
    //podriamos retornar el usuario
    ResponseEntity<?> generatePin(String email);
    User getByPin(String pin);
    ResponseEntity<?> updatePassword(ChangePasswordResource changePasswordResource);

}
