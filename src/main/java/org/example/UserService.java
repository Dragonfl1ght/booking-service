package org.example;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserStorage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userRepository;

    public User create(User user){
        return userRepository.create(user);
    }

    public User update(User user){
        Optional<User> byId = userRepository.findById(user.getId());
        User userOfOprional = byId.orElseThrow(() -> new RuntimeException("User not found: " + user.getId()));
        userOfOprional.setName(user.getName());
        userOfOprional.setEmail(user.getEmail());
        userRepository.update(userOfOprional);
        return userOfOprional;
    }

    public void delete(int id){
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));
        userRepository.delete(id);
    }
}
