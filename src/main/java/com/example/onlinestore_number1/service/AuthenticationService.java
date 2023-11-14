package com.example.onlinestore_number1.service;

import com.example.onlinestore_number1.dto.request.SignInRequest;
import com.example.onlinestore_number1.dto.request.SignUpRequest;
import com.example.onlinestore_number1.dto.responce.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
