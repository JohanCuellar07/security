package com.sena.security.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.security.DTO.Recovery_RequestDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.model.Recovery_Request;
import com.sena.security.repository.IRecovery_Request;

@Service
public class Recovery_RequestService {
    @Autowired
    private IRecovery_Request data;

    public List<Recovery_Request> findAll() {
        return data.findAll();
    }

    public Optional<Recovery_Request> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteRecovery_Request(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "Recovery_Request not found"
            );
            return respuesta;
        };
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.NOT_FOUND.toString(), 
            "Recovery_Request deleted"
        );
        return respuesta;
    }

    public responseDTO save(Recovery_RequestDTO recovery_RequestDTO) {
        if (recovery_RequestDTO.getUser().getId() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "User can't be empty"
            );
            return respuesta;
        }
        if (recovery_RequestDTO.getToken().length() < 1 || recovery_RequestDTO.getToken().length() > 255) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Token can't be empty or more than 255 characters"
            );
            return respuesta;
        }
        if (recovery_RequestDTO.getCreation().isBefore(LocalDate.now())) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Creation can't be before today"
            );
            return respuesta;
        }
        if (recovery_RequestDTO.getExpiration().isBefore(LocalDate.now())) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Expiration can't be before today"
            );
            return respuesta;
        }
        Recovery_Request recovery_RequestRegister = convertToModel(recovery_RequestDTO);
        data.save(recovery_RequestRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Recovery_Request created"
        );
        return respuesta;
    }

    public responseDTO updateRecovery_Request(int id, Recovery_RequestDTO recovery_RequestDTO) {
        Optional<Recovery_Request> recovery_RequestOpt = data.findById(id);
        if (!recovery_RequestOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Recovery_Request not found"
            );
            return respuesta;
        }
        Recovery_Request existingRecovery_Request = recovery_RequestOpt.get();
        existingRecovery_Request.setUser(recovery_RequestDTO.getUser());
        existingRecovery_Request.setToken(recovery_RequestDTO.getToken());
        existingRecovery_Request.setCreation(recovery_RequestDTO.getCreation());
        existingRecovery_Request.setExpiration(recovery_RequestDTO.getExpiration());
        existingRecovery_Request.setUsed(recovery_RequestDTO.getUsed());
        data.save(existingRecovery_Request);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Recovery_Request updated"
        );
        return respuesta;
    }

    public Recovery_RequestDTO convertToDTO(Recovery_Request recovery_Request) {
        Recovery_RequestDTO recovery_RequestDTO = new Recovery_RequestDTO(
            recovery_Request.getUser(),
            recovery_Request.getToken(),
            recovery_Request.getCreation(),
            recovery_Request.getExpiration(),
            recovery_Request.getUsed()
        );
        return recovery_RequestDTO;
    }

    public Recovery_Request convertToModel(Recovery_RequestDTO recovery_RequestDTO) {
        Recovery_Request recovery_Request = new Recovery_Request(
            0,
            recovery_RequestDTO.getUser(),
            recovery_RequestDTO.getToken(),
            recovery_RequestDTO.getCreation(),
            recovery_RequestDTO.getExpiration(),
            false
        );
        return recovery_Request;
    }
}
