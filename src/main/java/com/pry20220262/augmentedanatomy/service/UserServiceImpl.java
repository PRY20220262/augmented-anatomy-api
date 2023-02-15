package com.pry20220262.augmentedanatomy.service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.RestExceptionHandler;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Profile;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.repository.ProfileRepository;
import com.pry20220262.augmentedanatomy.repository.UserRepository;
import com.pry20220262.augmentedanatomy.resource.User.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender emailSender;

    @Value("${azure.blob.key}")
    private String storageConnectionAzure;

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(Error.USER_NOT_FOUND));
    }

    private User finByEmail(String email) {
        Optional<User> retrievedUser = userRepository.findByEmail(email);
        if (retrievedUser.isEmpty()) throw new UsernameNotFoundException("User not found :(");
        return retrievedUser.get();
    }

    @Override
    public User register(UserSaveResource userSaveResource) {
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

    @Override
    public ResponseEntity<?> generatePin(String email) {

        User user = finByEmail(email);
        int randomPIN = (int) (Math.random() * 9000) + 1000;

        user.setPin(String.valueOf(randomPIN));

        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("augmentedanatomyupc@gmail.com");
        message.setTo(email);
        message.setSubject("PIN Generado Augmented Anatomy");
        message.setText("Se ha generado correctamente el PIN: " + randomPIN);

        emailSender.send(message);

        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<?> validatePin(UserPinResource userPinResource) {
        Optional<User> retrievedUser = userRepository.findByPin(userPinResource.getPin());
        if (retrievedUser.isEmpty()) throw new UsernameNotFoundException("User not found :(");
        User user = retrievedUser.get();
        if (!Objects.equals(user.getEmail(), userPinResource.getEmail()))
            throw new ServiceException(Error.USER_PIN_NOT_MATCH);
        user.setPin(null);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updatePassword(ChangePasswordResource changePasswordResource) {
        User user = finByEmail(changePasswordResource.getEmail());

        user.setPassword(passwordEncoder.encode(changePasswordResource.getNewPassword()));
        user.setPin(null);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> changeOwnPassword(ChangeOwnPasswordResource changePasswordResource) {
        User user = finByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!passwordEncoder.matches(changePasswordResource.getOldPassword(), user.getPassword()))
            throw new ServiceException(Error.INVALID_OLD_PASSWORD);

        updatePassword(ChangePasswordResource.builder().newPassword(changePasswordResource.getNewPassword()).email(user.getEmail()).build());

        return ResponseEntity.ok().build();

    }

    @Override
    public String uploadProfilePhoto(UploadPhotoResource uploadPhotoResource, Long id) {
        String resultService;
        String nameContainer = "users";

        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);

            CloudBlob blob;
            blob = container.getBlockBlobReference(uploadPhotoResource.getName());
            byte[] decodedBytes = Base64.getDecoder().decode(uploadPhotoResource.getFileBase64());
            blob.uploadFromByteArray(decodedBytes, 0, decodedBytes.length);
            logger.error(blob.getUri().toString());

            resultService = "OK";
        } catch (Exception e) {
            resultService = e.getMessage();
        }

        return resultService;
    }


}
