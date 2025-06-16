package com.sena.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.security.model.AuthResponse;
import com.sena.security.model.LoginRequest;
import com.sena.security.model.RegisterRequest;
import com.sena.security.model.User;
import com.sena.security.repository.IUser;
import com.sena.security.repository.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IUserService {

    private final IUser data;
    private final JWTService jwtService;
    
    @Override
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


    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public int delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
