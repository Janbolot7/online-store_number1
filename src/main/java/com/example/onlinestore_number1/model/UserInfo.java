package com.example.onlinestore_number1.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank
    private String surname;
    @Min(value = 12, message = "Age should not be less than 12")
    @Max(value = 150, message = "Age should not be greater than 150")
    private Short age;
    @Email(message = "Email should be valid")
    private String email;
    private String phoneNumber;
    private String address;
}
