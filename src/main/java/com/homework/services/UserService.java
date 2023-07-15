package com.homework.services;

import com.homework.dto.User;
import com.homework.dto.UserPageRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    public abstract ResponseEntity<User> createUser(User user);
    public abstract ResponseEntity<User> updateUser(String id, User user);
    public abstract ResponseEntity<User> deleteUser(String id);
    public abstract ResponseEntity<User> getUserById(String id);
    public abstract ResponseEntity<List<User>> getAllUsers(String id);
    public abstract ResponseEntity<Map<String, Object>> getUsers(UserPageRequestDto userPageRequestDto);
}
