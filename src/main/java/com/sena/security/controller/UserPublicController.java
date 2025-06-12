package com.sena.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserPublicController {

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("end-point Publico login", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return new ResponseEntity<>("end-point Publico register", HttpStatus.OK);
    }
}
