package com.pry20220262.augmentedanatomy.service;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.RestExceptionHandler;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Profile;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.repository.ProfileRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import com.pry20220262.augmentedanatomy.resource.User.UserSaveResource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(Error.USER_NOT_FOUND));
    }

    @Override
    public User register(UserSaveResource userSaveResource) {
        logger.error("POR QUE ENTRA CARAJO");
        Optional<User> retrievedUser = userRepository.findByEmail(userSaveResource.getEmail());
        if (retrievedUser.isPresent()) throw new ServiceException(Error.USER_EXISTS);

        User user = new User();
        user.setEmail(userSaveResource.getEmail());
        user.setPassword(passwordEncoder.encode(userSaveResource.getPassword()));
        user = userRepository.save(user);

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setIsStudent(userSaveResource.getIsStudent());
        profile.setFullName(userSaveResource.getFullName());
        profile.setPhone(userSaveResource.getPhone());


        profileRepository.save(profile);

        return user;
    }


}
