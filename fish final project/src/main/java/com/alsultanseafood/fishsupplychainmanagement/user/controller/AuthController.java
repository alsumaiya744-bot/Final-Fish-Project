package com.alsultanseafood.fishsupplychainmanagement.user.controller;



import com.alsultanseafood.fishsupplychainmanagement.user.dto.LoginRequest;
import com.alsultanseafood
.fishsupplychainmanagement
.user.entity.User;

import com.alsultanseafood
.fishsupplychainmanagement
.user.service.UserService;
import com.alsultanseafood.fishsupplychainmanagement.user.dto.LoginResponse;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    private final UserService userService;

    

    private final UserRepository userRepository;

public AuthController(
        UserService userService,
        UserRepository userRepository) {

    this.userService =
            userService;

    this.userRepository =
            userRepository;
}

    // ================= Register =================

    @PostMapping("/register")
    public User register(
            @RequestBody User user) {

        return userService
                .register(user);
    }

    // ================= Login =================

    @PostMapping("/login")
public LoginResponse login(
        @RequestBody LoginRequest request) {

    String token =
            userService.login(
                    request.getEmail(),
                    request.getPassword(),
                    request.getRole());

    User user =
            userRepository
                    .findByEmail(
                            request.getEmail())
                    .orElseThrow();

    return new LoginResponse(
            token,
            user.getRole(),
            user.getUserId(),
            user.getFullName(),
            user.getEmail(),
        user.getPhoneNumber(),
        user.getAddress()
    );
}
}