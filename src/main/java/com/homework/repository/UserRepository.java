package com.homework.repository;

import com.homework.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUsernameContaining(String username);
    List<User> findByEmailContaining(String email);
    List<User> findByNameAndSurnameContaining(String name, String surname);
}