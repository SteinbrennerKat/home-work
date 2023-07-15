package com.homework.controller;

import com.homework.dto.User;
import com.homework.dto.UserPageRequestDto;
import com.homework.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/home-work-users")
public class UserController {

    @Autowired
    private UserServiceImpl service;


    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String username) {
        return service.getAllUsers(username);
    };

    @GetMapping("/users-page")
    public ResponseEntity<Map<String, Object>> getUsersPage(
            UserPageRequestDto userPageRequestDto
            ) {
        return service.getUsers(userPageRequestDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return service.getUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        return service.deleteUser(id);
    }
}
