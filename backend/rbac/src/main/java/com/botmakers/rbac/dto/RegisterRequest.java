package com.botmakers.rbac.dto;

import com.botmakers.rbac.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RegisterRequest {
    private String name,email,password;
    private Role role;
}
