package com.example.onlinestore_number1.dto.responce;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    @Column(name = "user_name", columnDefinition = "VARCHAR(255)")
    private String userName;
    @Column(name = "sure_name", columnDefinition = "VARCHAR(255)")
    private String sureName;
    @Column(name = "age", columnDefinition = "INT(11)")
    private Integer age;
    @Column(name = "gender", columnDefinition = "VARCHAR(255)")
    private String gender;
    @Column(name = "tel_number", columnDefinition = "INT(11)")
    private Integer telNumber;
    @Column(name = "address", columnDefinition = "VARCHAR(255")
    private String address;
}