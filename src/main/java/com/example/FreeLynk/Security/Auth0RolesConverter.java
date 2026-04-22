package com.example.FreeLynk.Security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Auth0RolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String ROLES_CLAIM = "https://freelynk-api/roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList(ROLES_CLAIM);
        if (roles == null) return Collections.emptyList();

        return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) 
            .collect(Collectors.toList());
    }
}
