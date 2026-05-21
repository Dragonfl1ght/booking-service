package org.example;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User update = userService.update(user);
        if (update == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }
}
