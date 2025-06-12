package com.sena.security.service;

import org.springframework.stereotype.Service;

import com.sena.security.model.AuthResponse;
import com.sena.security.model.LoginRequest;
import com.sena.security.model.RegisterRequest;
import com.sena.security.model.User;
import com.sena.security.repository.IUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUser data;
    private final JWTService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User userData = User.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(request.getPassword())
            .build();
        data.save(userData);
        return AuthResponse.builder()
            .token(jwtService.getToken(userData))
            .build();
    }

    public AuthResponse login(LoginRequest request) {
        return new AuthResponse();
    }
}
