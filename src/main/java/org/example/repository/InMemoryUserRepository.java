package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.utility.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserStorage {
    private final ConcurrentHashMap<Integer, User> idToUser = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    @Override
    public User create(User user){
        user.setId(idGenerator.nextId());
        idToUser.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(idToUser.values());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(idToUser.get(id));
    }

    @Override
    public User update(User user){
        idToUser.put(user.getId(), user);
        return user;
    }

    @Override
    public User delete(Integer id){
        User user = idToUser.get(id);
        idToUser.remove(id);
        return user;
    }

    @Override
    public boolean isExist(Integer id) {
        return idToUser.containsKey(id);
    }
}
