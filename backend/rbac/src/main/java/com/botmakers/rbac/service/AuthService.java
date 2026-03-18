package com.botmakers.rbac.service;

import com.botmakers.rbac.dto.LoginRequest;
import com.botmakers.rbac.dto.RegisterRequest;
import com.botmakers.rbac.entity.User;
import com.botmakers.rbac.repository.UserRepository;
import com.botmakers.rbac.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    //registerUser
    public void register(RegisterRequest request){
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
    }

    //loginUser
    public String login(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("User Not Found "));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
