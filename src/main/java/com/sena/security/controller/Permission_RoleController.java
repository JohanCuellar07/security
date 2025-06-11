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

import com.sena.security.DTO.Permission_RoleDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.service.Permission_RoleService;

@RestController
@RequestMapping("/permission_role")
public class Permission_RoleController {
    @Autowired
    private Permission_RoleService permission_RoleService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPermission_Role(@RequestBody Permission_RoleDTO permission_RoleDTO) {
        responseDTO respuesta = permission_RoleService.save(permission_RoleDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePermission_Role(@PathVariable int id, @RequestBody Permission_RoleDTO permission_RoleDTO) {
        responseDTO respuesta = permission_RoleService.updatePermission_Role(id, permission_RoleDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPermission_Roles() {
        var listPermission_Roles = permission_RoleService.findAll();
        return new ResponseEntity<>(listPermission_Roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePermission_Role(@PathVariable int id) {
        var permission_Role = permission_RoleService.findById(id);
        return new ResponseEntity<>(permission_Role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePermission_Role(@PathVariable int id) {
        responseDTO respuesta = permission_RoleService.deletePermission_Role(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
