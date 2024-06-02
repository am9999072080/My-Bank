package com.am.MyBank.repository;

import com.am.MyBank.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailAndAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String email);
}
