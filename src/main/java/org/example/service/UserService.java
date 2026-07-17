package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UserNotFoundException;
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
                .orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.create(user);
    }

    public User update(User user){
        log.debug("Выполняется обновление пользователя {}", user);
        Integer userId = user.getId();
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("При обновлении пользователь с id = %d не найден", userId)));
        user = userRepository.update(user);
        log.debug("Пользователь успешно обновлен {}", user);
        return user;
    }

    public User delete(int id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("При удалении пользователь с id = %d не найден" + id));
        userRepository.delete(id);
        return user;
    }
}
