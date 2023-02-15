package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.*;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User findById(Long id);
    User register(UserSaveResource user);
    //podriamos retornar el usuario
    ResponseEntity<?> generatePin(String email);
    ResponseEntity<?> validatePin(UserPinResource userPinResource);
    ResponseEntity<?> updatePassword(ChangePasswordResource changePasswordResource);
    ResponseEntity<?> changeOwnPassword(ChangeOwnPasswordResource changePasswordResource);
    String uploadProfilePhoto(UploadPhotoResource uploadPhotoResource, Long id);

}
