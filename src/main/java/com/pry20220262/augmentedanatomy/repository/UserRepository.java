package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
