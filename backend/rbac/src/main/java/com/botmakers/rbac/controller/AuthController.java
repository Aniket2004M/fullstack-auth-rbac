
package com.botmakers.rbac.controller;

import com.botmakers.rbac.dto.AuthResponse;
import com.botmakers.rbac.dto.LoginRequest;
import com.botmakers.rbac.dto.RegisterRequest;
import com.botmakers.rbac.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //register-api
    @PostMapping("/public/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.ok("user registered");
    }

    //login-api
    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
       String token= authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // USER rndpoint
    @GetMapping("/user")
    public String userEndpoint(){
        return "Hello User !!!! this is the USER endpoint";
    }

    // ADMIN endpoint
    @GetMapping("/admin")
    public String adminEndpoint(){
        return "hello ADMIN ! ";
    }
}
