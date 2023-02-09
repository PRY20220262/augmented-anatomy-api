package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.model.User;

public interface UserService {
    User findById(Long id);

    User register(User user);


}
