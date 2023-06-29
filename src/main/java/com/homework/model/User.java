package com.homework.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String hash;
    private String password;
    private String email;
    private String username;
    private String age;
    private String sex;
    private String createdDate;
    private String updatedDate;

    public User() {
    }

    public User(
            String name,
            String surname,
            String hash,
            String password,
            String email,
            String username,
            String age,
            String sex
    ) {
        this.name = name;
        this.surname = surname;
        this.hash = hash;
        this.password = password;
        this.email = email;
        this.username = username;
        this.age = age;
        this.sex = sex;
    }

    public User withCreatedDate() {
        this.createdDate = Instant.now().toString();
        return this;
    }

    public User withUpdatedDate() {
        this.updatedDate = Instant.now().toString();
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHash() {
        return hash;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUpdatedDate() {
        this.updatedDate = Instant.now().toString();
    }

    @Override
    public String toString() {
        return "User[" +
                "id='" + id + " name: " + name + ", surname: " + surname + "]";
    }
}

