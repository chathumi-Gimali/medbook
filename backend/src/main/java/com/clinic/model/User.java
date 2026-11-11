package com.clinic.model;

// Abstract base class: every user type (patient, doctor, admin) shares these fields.
// You can't create a plain "User" object, only a subclass like Patient.
public abstract class User {

    // Private fields = encapsulation: other classes must use getters and setters
    private String id;
    private String name;
    private String email;
    private String password;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // No setter for id: once created, an ID shouldn't change

    // Password: only a getter for checking login, no public setter
    // (this is a small security habit worth mentioning in the viva)
    public String getPassword() { return password; }

    // Polymorphism: every subclass must say what role it has
    public abstract String getRole();
}