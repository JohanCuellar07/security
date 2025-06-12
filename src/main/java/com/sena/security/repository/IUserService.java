package com.sena.security.repository;

import java.util.List;
import java.util.Optional;

import com.sena.security.model.RegisterRequest;
import com.sena.security.model.User;

public interface IUserService {

    public User register(RegisterRequest request);
    public Optional<User> findByUsername(String username);
    public int delete(String id);
    public List<User> findAll();
}
