package com.homework.controller;

import com.homework.model.User;
import com.homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/home-work-users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String username) {
        try {
            List<User> users = new ArrayList<User>();
            if (username!= null) {
                users.addAll(userRepository.findByUsernameContaining(username));
            } else {
                users.addAll(userRepository.findAll());
            }

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    @GetMapping("/users-page")
    public ResponseEntity<Map<String, Object>> getUsersPage(
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            List<User> users;
            Pageable paging = PageRequest.of(page, size);

            Page<User> usersPage;
            if(username != null ) {
                usersPage = userRepository.findByUsernameContaining(username, paging);
            } else {
                usersPage = userRepository.findAll(paging);
            }

            users = usersPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", users);
            response.put("totalElements", usersPage.getTotalElements());
            response.put("totalPages", usersPage.getTotalPages());
            response.put("size", usersPage.getSize());
            response.put("number", usersPage.getNumber());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository.save(new User(
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole()
                    ));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get().withUpdatedDate();
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getUsername());
            _user.setRole(user.getRole());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user-delete-all")
    public ResponseEntity<User> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/count")
    public ResponseEntity<Long> countUsers() {
        try {
            return new ResponseEntity<>(userRepository.count(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
