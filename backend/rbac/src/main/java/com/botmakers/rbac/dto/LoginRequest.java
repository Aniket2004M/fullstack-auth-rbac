package com.botmakers.rbac.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email,password;
}
