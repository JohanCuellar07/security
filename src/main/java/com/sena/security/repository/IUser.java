package com.sena.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.security.model.User;

public interface IUser extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
