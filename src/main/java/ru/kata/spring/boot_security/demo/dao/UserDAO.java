package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface UserDAO {
    void saveUser(User user);

    List<User> findAll();

    void deleteById(Integer id);

    Optional<User> findById(int id);

    void updateUser(User user);
    Optional<User> findByUsername(String username);
}
