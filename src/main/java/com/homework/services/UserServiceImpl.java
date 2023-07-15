package com.homework.services;

import com.homework.dto.User;
import com.homework.dto.UserPageRequestDto;
import com.homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> createUser(User user) {
        try {
            User _user = userRepository.save(new User(
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getRole(),
                    user.getAge()
            ));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> updateUser(String id, User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get().withUpdatedDate();
            _user.setName(user.getName());
            _user.setSurname(user.getSurname());
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getUsername());
            _user.setRole(user.getRole());
            _user.setAge(user.getAge());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<User> deleteUser(String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(String username) {
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
    }

    @Override
    public ResponseEntity<Map<String, Object>> getUsers(UserPageRequestDto userPageRequestDto) {
        var direction = userPageRequestDto.getDirection();
        var page = userPageRequestDto.getPage();
        var size = userPageRequestDto.getSize();
        var createdDate = userPageRequestDto.getCreatedDate();
        var sortOrder = userPageRequestDto.getSortOrder();
        try {
            Sort sort = Sort.by(Objects.equals(direction, "DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortOrder);
            List<User> users;
            Pageable paging = PageRequest.of(page, size, sort);
            Page<User> usersPage;
            usersPage = userRepository.findByCreatedDateGreaterThan(createdDate != null ? Long.parseLong(createdDate) : Instant.now().toEpochMilli() - TimeUnit.DAYS.toMillis(365), paging);
            users = usersPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("users", users);
            response.put("totalElements", usersPage.getTotalElements());
            response.put("totalPages", usersPage.getTotalPages());
            response.put("size", usersPage.getSize());
            response.put("number", usersPage.getNumber());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
