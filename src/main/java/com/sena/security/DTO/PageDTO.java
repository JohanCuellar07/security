package com.sena.security.DTO;

public class PageDTO {
    private String name;

    private String url;

    public PageDTO() {
    }

    public PageDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
