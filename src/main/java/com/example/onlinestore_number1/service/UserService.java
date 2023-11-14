package com.example.onlinestore_number1.service;

import com.example.onlinestore_number1.dto.responce.UserResponse;
import com.example.onlinestore_number1.exception.NotFoundException;
import com.example.onlinestore_number1.model.User;
import com.example.onlinestore_number1.repository.UserRepository;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::restrictedDataToUserResponse).toList();
    }

    public UserResponse getUserById(Long userId) {
//        return userRepository.findById(userId);
        User user = checkUserAndReturnUser(userId);
        return restrictedDataToUserResponse(user);
    }
    private User checkUserAndReturnUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Не найден пользователь по этому ID = " + id)
        );
    }
    //Предоставить ограниченные данные для защиты инфы моих юзеров
    public UserResponse restrictedDataToUserResponse(User user) {
        return UserResponse.builder()
                .userName(user.getUsername())
                .userInfo(user.getInfo())
                .build();
    }

    public User createUser(User user) {
        User.builder()
                .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                                .role(Role.ROLE_USER)
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setInfo(updatedUser.getInfo());
                    user.setRole(updatedUser.getRole());
                    user.setMailing(updatedUser.isMailing());
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}