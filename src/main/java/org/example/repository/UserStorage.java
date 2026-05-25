package org.example.repository;

import org.example.model.User;

import java.util.Optional;

public interface UserStorage {
    User create(User user);
    Optional<User> findById(Integer id);
    User update(User user);
    void delete(int id);
}
