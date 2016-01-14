package com.pb.task.manager.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_DEVELOPER("Разработчик"),
    ROLE_MANAGER("Менеджер");

    private final String roleViewName;

    Role(final String s) {
        roleViewName = s;
    }

    public String getRoleViewName() {
        return roleViewName;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return this.roleViewName;
    }
}