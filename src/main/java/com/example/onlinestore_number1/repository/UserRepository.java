package com.example.onlinestore_number1.repository;

import com.example.onlinestore_number1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUserName(String userName);
}
