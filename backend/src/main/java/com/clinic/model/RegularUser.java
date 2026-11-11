package com.clinic.model;

// Inheritance: a regular user is a User with a membership type.
public class RegularUser extends User {

    private String membershipType; // e.g. "STANDARD" or "PREMIUM"

    public RegularUser(String id, String name, String email, String password, String membershipType) {
        super(id, name, email, password);
        this.membershipType = membershipType;
    }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    // Polymorphism: every RegularUser reports the REGULAR role
    @Override
    public String getRole() { return "REGULAR"; }
}