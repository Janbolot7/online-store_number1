package com.example.onlinestore_number1.dto.request;

import com.example.onlinestore_number1.Enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String login;
    private String password;
    private String numberPhone;
    private Role role;
}
