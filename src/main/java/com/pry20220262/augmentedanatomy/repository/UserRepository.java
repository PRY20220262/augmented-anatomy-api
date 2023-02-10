package com.pry20220262.augmentedanatomy.repository;

import com.pry20220262.augmentedanatomy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String QUERY_FIND_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email ";
    @Query(value = QUERY_FIND_BY_EMAIL)
    Optional<User> findByEmail(@Param("email") String email);

    String QUERY_FIND_BY_PIN = "SELECT u FROM User u WHERE u.pin = :pin ";
    @Query(value = QUERY_FIND_BY_PIN)
    Optional<User> findByPin(@Param("pin") String pin);

}
