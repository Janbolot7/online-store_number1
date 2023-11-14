package com.example.onlinestore_number1.service.impl;

import com.example.onlinestore_number1.Enums.Role;
import com.example.onlinestore_number1.dto.request.SignInRequest;
import com.example.onlinestore_number1.dto.request.SignUpRequest;
import com.example.onlinestore_number1.dto.responce.JwtAuthenticationResponse;
import com.example.onlinestore_number1.model.User;
import com.example.onlinestore_number1.repository.UserRepository;
import com.example.onlinestore_number1.service.AuthenticationService;
import com.example.onlinestore_number1.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        var user = userRepository.findByUserName(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .username(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .numberPhone(request.getNumberPhone())
                .build();
        user = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
