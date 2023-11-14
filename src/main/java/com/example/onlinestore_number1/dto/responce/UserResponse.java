package com.example.onlinestore_number1.dto.responce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userName;
    private UserInfoResponse userInfo;
}
