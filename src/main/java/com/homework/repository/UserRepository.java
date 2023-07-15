package com.homework.repository;

import com.homework.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    Page<User> findByUsernameContaining(String username, Pageable pageable);
    @Query("{'createdDate' : { $gt: ?0 }}")
    Page<User> findByCreatedDateGreaterThan(Long min, Pageable pageable);
    Page<User> findAll(Pageable pageable);
    List<User> findByUsernameContaining(String username);

}