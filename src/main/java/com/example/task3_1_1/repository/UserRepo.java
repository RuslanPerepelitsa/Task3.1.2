package com.example.task3_1_1.repository;

import com.example.task3_1_1.model.User;

import java.util.List;

public interface UserRepo {
    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();
}
