package com.example.onlinestore_number1.Enums;

import com.sun.jdi.PrimitiveValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role{
    ADMIN("ADMIN"),

    USER("USER");


    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}