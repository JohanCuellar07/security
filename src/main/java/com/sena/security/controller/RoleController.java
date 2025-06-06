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

import com.sena.security.DTO.RoleDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Object> registerRole(@RequestBody RoleDTO roleDTO) {
        responseDTO respuesta = roleService.save(roleDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        responseDTO respuesta = roleService.updateRole(id, roleDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllRoles() {
        var listRoles = roleService.findAll();
        return new ResponseEntity<>(listRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRole(@PathVariable int id) {
        var role = roleService.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable int id) {
        responseDTO respuesta = roleService.deleteRole(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
