package com.botmakers.rbac.dto;

import com.botmakers.rbac.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name,email,password;
    private Role role;
}
