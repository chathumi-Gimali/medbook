package com.clinic.model;

// Inheritance: an admin is a User with extra privileges.
public class AdminUser extends User {

    private String permissions; // e.g. "FULL" or "APPOINTMENTS_ONLY"

    public AdminUser(String id, String name, String email, String password, String permissions) {
        super(id, name, email, password);
        this.permissions = permissions;
    }

    public String getPermissions() { return permissions; }
    public void setPermissions(String permissions) { this.permissions = permissions; }

    // Polymorphism: every AdminUser reports the ADMIN role
    @Override
    public String getRole() { return "ADMIN"; }
}