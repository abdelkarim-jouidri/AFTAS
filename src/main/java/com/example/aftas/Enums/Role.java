package com.example.aftas.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADHERENT,
    MANAGER,
    JURY;

    @Override
    public String getAuthority() {
        return name();
    }
}
