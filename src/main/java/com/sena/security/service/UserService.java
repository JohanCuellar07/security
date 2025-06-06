package com.sena.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.security.DTO.UserDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.model.User;
import com.sena.security.repository.IUser;

@Service
public class UserService {
    @Autowired
    private IUser data;

    public List<User> findAll() {
        return data.findAll();
    }

    public Optional<User> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteUser(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "User not found"
            );
            return respuesta;
        };
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "User deleted"
        );
        return respuesta;
    }

    public responseDTO save(UserDTO userDTO) {
        if (userDTO.getUsername().length() < 1 || userDTO.getUsername().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Username can't be empty or more than 50 characters"
            );
            return respuesta;
        }
        if (userDTO.getEmail().length() < 1 || userDTO.getEmail().length() > 100) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Email can't be empty or more than 100 characters"
            );
            return respuesta;
        }
        if (userDTO.getPassword().length() < 1 || userDTO.getPassword().length() > 255) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Password can't be empty or more than 255 characters"
            );
            return respuesta;
        }
        if (userDTO.getRole().getId() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Role can't be empty"
            );
            return respuesta;
        }
        User userRegister = convertToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "User created"
        );
        return respuesta;
    }

    public responseDTO updateUser(int id, UserDTO userDTO) {
        Optional<User> userOpt = data.findById(id);
        if (!userOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "User not found"
            );
            return respuesta;
        }
        User existingUser = userOpt.get();
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());
        data.save(existingUser);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "User updated"
        );
        return respuesta;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO(
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getRole()
        );
        return userDTO;
    }

    public User convertToModel(UserDTO userDTO) {
        User user = new User(
            0,
            userDTO.getUsername(),
            userDTO.getEmail(),
            userDTO.getPassword(),
            userDTO.getRole()
        );
        return user;
    }
}

