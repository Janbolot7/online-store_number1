package com.example.onlinestore_number1.configuration;

import com.example.onlinestore_number1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConfig implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserDetailsConfig(UserRepository repository) {
        this.repository = repository;
    }

    private final static String USER_NOT_FOUND = "пользователь с таким именем не найден";

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return (UserDetails) repository.findByUserName(userName)
                .orElseThrow(
                        ()-> new  UsernameNotFoundException(String.
                                format(USER_NOT_FOUND, userName)));
    }
}
