package com.dev.authservice.model;

public enum ContasRole {
    ADMIN("ROLE_ADMIN"),
    PROFESSOR("ROLE_PROFESSOR"),
    USER("ROLE_USER");

    private final String role;

    ContasRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
