package com.example.onlinestore_number1.controller;

import com.example.onlinestore_number1.dto.responce.UserResponse;
import com.example.onlinestore_number1.model.User;
import com.example.onlinestore_number1.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Получение всех пользователей
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Получение пользователя по ID
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('USER")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание нового пользователя
    @PostMapping
    @PreAuthorize("hasAuthority('USER")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Обновление информации о пользователе
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('USER")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.getUserById(userId)
                .map(existingUser -> {
                    User updated = userService.updateUser(userId, updatedUser);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление пользователя по ID
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('USER")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
