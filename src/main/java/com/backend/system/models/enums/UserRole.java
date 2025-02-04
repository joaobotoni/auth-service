package com.backend.system.models.enums;

public enum UserRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String role;

    UserRole(String roleName) {
        this.role = roleName;
    }

    public String getRoleName() {
        return role;
    }
}
