package com.sena.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.security.model.Permission_Role;

public interface IPermission_Role extends JpaRepository<Permission_Role, Integer> {

}
