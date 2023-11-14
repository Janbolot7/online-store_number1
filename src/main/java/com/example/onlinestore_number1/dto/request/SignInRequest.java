package com.example.onlinestore_number1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String login;
    private String password;
}
