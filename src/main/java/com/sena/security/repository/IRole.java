package com.sena.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.security.model.Role;

public interface IRole extends JpaRepository<Role, Integer> {

}
