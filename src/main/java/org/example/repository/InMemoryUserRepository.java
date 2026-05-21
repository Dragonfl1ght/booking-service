package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.utility.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserStorage{
    private final ConcurrentHashMap<Integer, User> idToUser = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    public User create(User user){
        user.setId(idGenerator.nextId());
        idToUser.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return idToUser.getOrDefault(id, null);
    }

    public User update(User user){
        idToUser.put(user.getId(), user);
        return user;
    }
}
