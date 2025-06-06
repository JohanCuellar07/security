package com.sena.security.DTO;

import com.sena.security.model.Page;
import com.sena.security.model.Role;

public class Permission_RoleDTO {
    private Page page;

    private Role role;

    public Permission_RoleDTO() {
    }

    public Permission_RoleDTO(Page page, Role role) {
        this.page = page;
        this.role = role;
    }
    
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
