package com.sena.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.security.model.Page;

public interface IPage extends JpaRepository<Page, Integer> {

}
