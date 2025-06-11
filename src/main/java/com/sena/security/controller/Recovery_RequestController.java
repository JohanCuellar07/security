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

import com.sena.security.DTO.Recovery_RequestDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.service.Recovery_RequestService;

@RestController
@RequestMapping("/recovery_request")
public class Recovery_RequestController {
    @Autowired
    private Recovery_RequestService recovery_RequestService;

    @PostMapping("/")
    public ResponseEntity<Object> registerRecovery_Request(@RequestBody Recovery_RequestDTO recovery_RequestDTO) {
        responseDTO respuesta = recovery_RequestService.save(recovery_RequestDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRecovery_Request(@PathVariable int id, @RequestBody Recovery_RequestDTO recovery_RequestDTO) {
        responseDTO respuesta = recovery_RequestService.updateRecovery_Request(id, recovery_RequestDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllRecovery_Requests() {
        var listRecovery_Requests = recovery_RequestService.findAll();
        return new ResponseEntity<>(listRecovery_Requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRecovery_Request(@PathVariable int id) {
        var recovery_Request = recovery_RequestService.findById(id);
        return new ResponseEntity<>(recovery_Request, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecovery_Request(@PathVariable int id) {
        responseDTO respuesta = recovery_RequestService.deleteRecovery_Request(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
