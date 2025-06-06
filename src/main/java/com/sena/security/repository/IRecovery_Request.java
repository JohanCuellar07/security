package com.sena.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.security.model.Recovery_Request;

public interface IRecovery_Request extends JpaRepository<Recovery_Request, Integer> {

}
