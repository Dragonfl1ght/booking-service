package org.example.repository;

import org.example.model.User;

public interface UserStorage {
    User create(User user);
}
