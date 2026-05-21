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

    public User update(User user){
        User byId = userRepository.findById(user.getId());
        if (byId == null) {
            System.out.println("User not found");
            return null;
        }
        byId.setName(user.getName());
        byId.setEmail(user.getEmail());
        userRepository.update(byId);
        return byId;
    }
}
