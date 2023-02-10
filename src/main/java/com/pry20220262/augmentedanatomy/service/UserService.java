package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.ChangeOwnPasswordResource;
import com.pry20220262.augmentedanatomy.resource.User.ChangePasswordResource;
import com.pry20220262.augmentedanatomy.resource.User.UserPinResource;
import org.springframework.http.ResponseEntity;
import com.pry20220262.augmentedanatomy.resource.User.UserSaveResource;

public interface UserService {
    User findById(Long id);
    User register(UserSaveResource user);
    //podriamos retornar el usuario
    ResponseEntity<?> generatePin(String email);
    ResponseEntity<?> validatePin(UserPinResource userPinResource);
    ResponseEntity<?> updatePassword(ChangePasswordResource changePasswordResource);

    ResponseEntity<?> changeOwnPassword(ChangeOwnPasswordResource changePasswordResource);

}
