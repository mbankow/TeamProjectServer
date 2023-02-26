package com.team.project.user.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_MENTOR,
    ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
