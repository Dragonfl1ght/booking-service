package org.example;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserStorage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userRepository;

    public User create(User user){
        return userRepository.create(user);
    }
}
