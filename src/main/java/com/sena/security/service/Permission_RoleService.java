package com.sena.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.security.DTO.Permission_RoleDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.model.Permission_Role;
import com.sena.security.repository.IPermission_Role;

@Service
public class Permission_RoleService {
    @Autowired
    private IPermission_Role data;

    public List<Permission_Role> findAll() {
        return data.findAll();
    }

    public Optional<Permission_Role> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deletePermission_Role(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Permission_Role not found"
            );
            return respuesta;
        };
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Permission_Role deleted"
        );
        return respuesta;
    }

    public responseDTO save(Permission_RoleDTO permission_RoleDTO) {
        if (permission_RoleDTO.getPage().getId() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Page can't be empty"
            );
            return respuesta;
        }
        if (permission_RoleDTO.getRole().getId() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Role can't be empty"
            );
            return respuesta;
        }
        Permission_Role permission_RoleRegister = convertToModel(permission_RoleDTO);
        data.save(permission_RoleRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Permission_Role created"
        );
        return respuesta;
    }
    
    public responseDTO updatePermission_Role(int id, Permission_RoleDTO permission_RoleDTO) {
        Optional<Permission_Role> permission_RoleOpt = data.findById(id);
        if (!permission_RoleOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Permission_Role not found"
            );
            return respuesta;
        }
        Permission_Role existingPermission_Role = permission_RoleOpt.get();
        existingPermission_Role.setPage(permission_RoleDTO.getPage());
        existingPermission_Role.setRole(permission_RoleDTO.getRole());
        data.save(existingPermission_Role);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Permission_Role updated"
        );
        return respuesta;
    }

    public Permission_RoleDTO convertToDTO(Permission_Role permission_Role) {
        Permission_RoleDTO permission_RoleDTO = new Permission_RoleDTO(
            permission_Role.getPage(),
            permission_Role.getRole()
        );
        return permission_RoleDTO;
    }

    public Permission_Role convertToModel(Permission_RoleDTO permission_RoleDTO) {
        Permission_Role permission_Role = new Permission_Role(
            0,
            permission_RoleDTO.getPage(),
            permission_RoleDTO.getRole()
        );
        return permission_Role;
    }
}
