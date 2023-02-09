package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.resource.User.UserSaveResource;

public interface UserService {
    User findById(Long id);

    User register(UserSaveResource user);




}
