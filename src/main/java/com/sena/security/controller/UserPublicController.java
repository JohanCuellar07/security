package com.sena.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.security.model.AuthResponse;
import com.sena.security.model.LoginRequest;
import com.sena.security.model.RegisterRequest;
import com.sena.security.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserPublicController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
    }
}
