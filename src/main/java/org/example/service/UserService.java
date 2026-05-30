package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.NotFoundException;
import org.example.model.User;
import org.example.repository.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userRepository;

    public User findById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.create(user);
    }

    public User update(User user){
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User not found: " + user.getId()));
        userRepository.update(user);
        return user;
    }

    public User delete(int id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        userRepository.delete(id);
        return user;
    }
}
