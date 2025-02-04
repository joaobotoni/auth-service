package com.backend.system.models.enums;

public enum UserRole {
    USER("user"), ADMIN("admin");

    private final String role;

    UserRole(String roleName) {
        this.role = roleName;
    }

    public String getRoleName() {
        return role;
    }
}
