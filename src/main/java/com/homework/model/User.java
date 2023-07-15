package com.homework.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Version
    private Long version;
    private String name;
    private String surname;

    private String role;
    @Indexed(name = "email_index", unique = true)
    private String email;
    private String username;

    @CreatedDate
    private Long createdDate;
    @LastModifiedDate
    private Long updatedDate;

    public User(
            String name,
            String surname,
            String email,
            String username,
            String role
    ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.role = role;
        this.createdDate = Instant.now().toEpochMilli();
    }

    public User withUpdatedDate() {
        if (version >= 0) {
            this.updatedDate = Instant.now().toEpochMilli();
        }
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

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public Long getCreatedDate() {
        return createdDate;
    }
    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User[" +
                "id='" + id + " name: " + name + ", surname: " + surname + "]";
    }
}

