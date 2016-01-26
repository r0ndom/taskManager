package com.pb.task.manager.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_DEVELOPER("Разработчик"),
    ROLE_MANAGER("Менеджер"),
    ROLE_ADMIN("Администратор"),
    ROLE_TESTER("Тестировщик"),
    ROLE_CONTROL("Control");

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


    public static Role[] getPermittedValues() {
        return new Role[] {ROLE_ADMIN, ROLE_DEVELOPER, ROLE_MANAGER, ROLE_TESTER};
    }
}