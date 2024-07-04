package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    User findByUsername(String username);

    void saveUser(User user);

    List<User> getAllUsers();

    User showUserById(int id);

    void updateUserById(int id, User updateUser);

    User getUser(int id);

    void deleteUserById(int id);
}