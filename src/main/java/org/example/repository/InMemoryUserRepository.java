package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.utility.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserStorage {
    private final ConcurrentHashMap<Integer, User> idToUser = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    public User create(User user){
        user.setId(idGenerator.nextId());
        idToUser.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(idToUser.get(id));
    }

    public User update(User user){
        idToUser.put(user.getId(), user);
        return user;
    }

    public void delete(int id){
        idToUser.remove(id);
    }
}
