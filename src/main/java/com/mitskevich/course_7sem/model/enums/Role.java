package com.mitskevich.course_7sem.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum  Role {
    ADMIN, USER;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Stream.of(new SimpleGrantedAuthority(this.name())).collect(Collectors.toSet());
    }
}
