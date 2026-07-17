package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Meeting;
import org.example.model.User;
import org.example.service.MeetingService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// TRACE Максимально подробная информация для отладки: значения переменных, вход/выход из методов, детали выполнения. Обычно отключен в production.
// DEBUG Информация для разработчиков, помогающая диагностировать проблемы.
// INFO Обычные события работы приложения: запуск, остановка, успешное выполнение операций.
// WARN Предупреждения: ситуация нестандартная, но приложение продолжает работать.
// ERROR Ошибки, из-за которых не удалось выполнить конкретную операцию, но приложение может продолжать работу.
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        log.info("Получен запрос на создание пользователя: {}", user);
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        log.info("Получен запрос на обновление пользователя: {}", user);
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id){
        log.info("Получен запрос на удаление пользователя: {}", id);
        User deletedUser = userService.delete(id);
        return ResponseEntity.ok(deletedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        log.info("Получен запрос на поиск пользователя: {}", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        log.info("Получен запрос на поиск всех пользователей");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}/meetings")
    public ResponseEntity<List<Meeting>> findAllAvailableMeetingsByOwnerId(@PathVariable Integer id) {
        log.info("Получен запрос на поиск всех встреч пользователя с id = {}", id);
        return ResponseEntity.ok(meetingService.findAllByOwnerId(id));
    }
}