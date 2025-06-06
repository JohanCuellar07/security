package com.sena.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.security.DTO.RoleDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.model.Role;
import com.sena.security.repository.IRole;

@Service
public class RoleService {
    @Autowired
    private IRole data;

    public List<Role> findAll() {
        return data.findAll();
    }

    public Optional<Role> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteRole(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Role not found"
            );
            return respuesta;
        };
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Role deleted"
        );
        return respuesta;
    }

    public responseDTO save(RoleDTO roleDTO) {
        if (roleDTO.getName().length() < 1 || roleDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Role name can't be empty or more than 50 characters"
            );
            return respuesta;
        }
        if (roleDTO.getDescription().length() > 255) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Role description can't be more than 255 characters"
            );
            return respuesta;
        }
        Role roleRegister = convertToModel(roleDTO);
        data.save(roleRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Role created"
        );
        return respuesta;
    }

    public responseDTO updateRole(int id, RoleDTO roleDTO) {
        Optional<Role> roleOpt = data.findById(id);
        if (!roleOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Role not found"
            );
            return respuesta;
        }
        Role existingRole = roleOpt.get();
        existingRole.setName(roleDTO.getName());
        existingRole.setDescription(roleDTO.getDescription());
        data.save(existingRole);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Role updated"
        );
        return respuesta;
    }

    public RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO(
            role.getName(),
            role.getDescription()
        );
        return roleDTO;
    }

    public Role convertToModel(RoleDTO roleDTO) {
        Role role = new Role(
            0,
            roleDTO.getName(),
            roleDTO.getDescription()
        );
        return role;
    }
}
