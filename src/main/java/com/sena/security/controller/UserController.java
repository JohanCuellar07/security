package com.sena.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.security.DTO.UserDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
/* 
    @PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) {
        responseDTO respuesta = userService.save(userDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        responseDTO respuesta = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
*/
    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        var listUsers = userService.findAll();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        responseDTO respuesta = userService.deleteUser(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        return new ResponseEntity<>("end-point privado profile", HttpStatus.OK);
    }
}
