package com.sena.security.DTO;

import java.time.LocalDate;

import com.sena.security.model.User;

public class Recovery_RequestDTO {
    private User user;

    private String token;

    private LocalDate creation;

    private LocalDate expiration;

    private Boolean used;
    
    public Recovery_RequestDTO() {
    }

    public Recovery_RequestDTO(User user, String token, LocalDate creation, LocalDate expiration, Boolean used) {
        this.user = user;
        this.token = token;
        this.creation = creation;
        this.expiration = expiration;
        this.used = used;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

}
