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

import com.sena.security.DTO.PageDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.service.PageService;

@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageService pageService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPage(@RequestBody PageDTO pageDTO) {
        responseDTO respuesta = pageService.save(pageDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePage(@PathVariable int id, @RequestBody PageDTO pageDTO) {
        responseDTO respuesta = pageService.updatePage(id, pageDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPages() {
        var listPages = pageService.findAll();
        return new ResponseEntity<>(listPages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePage(@PathVariable int id) {
        var page = pageService.findById(id);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePage(@PathVariable int id) {
        responseDTO respuesta = pageService.deletePage(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
