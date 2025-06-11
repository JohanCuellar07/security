package com.sena.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.security.DTO.PageDTO;
import com.sena.security.DTO.responseDTO;
import com.sena.security.model.Page;
import com.sena.security.repository.IPage;

@Service
public class PageService {
    @Autowired
    private IPage data;

    public List<Page> findAll() {
        return data.findAll();
    }

    public Optional<Page> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deletePage(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Page not found"
            );
            return respuesta;
        };
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Page deleted"
        );
        return respuesta;
    }

    public responseDTO save(PageDTO pageDTO) {
        if (pageDTO.getName().length() < 1 || pageDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Page name can't be empty or more than 50 characters"
            );
            return respuesta;
        }
        if (pageDTO.getUrl().length() < 1 || pageDTO.getUrl().length() > 255) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Page url can't be empty or more than 255 characters"
            );
            return respuesta;
        }
        Page pageRegister = convertToModel(pageDTO);
        data.save(pageRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Page created"
        );
        return respuesta;
    }

    public responseDTO updatePage(int id, PageDTO pageDTO) {
        Optional<Page> pageOpt = data.findById(id);
        if (!pageOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Page not found"
            );
            return respuesta;
        }
        Page existingPage = pageOpt.get();
        existingPage.setName(pageDTO.getName());
        existingPage.setUrl(pageDTO.getUrl());
        data.save(existingPage);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Page updated"
        );
        return respuesta;
    }

    public PageDTO convertToDTO(Page page) {
        PageDTO pageDTO = new PageDTO(
            page.getName(),
            page.getUrl()
        );
        return pageDTO;
    }

    public Page convertToModel(PageDTO pageDTO) {
        Page page = new Page(
            0,
            pageDTO.getName(),
            pageDTO.getUrl()
        );
        return page;
    }
}
