package com.clinic.model;

// Inheritance: Patient gets id, name, email, password from User
// and adds its own field, phone.
public class Patient extends User {

    private String phone;

    public Patient(String id, String name, String email, String password, String phone) {
        // super(...) passes the shared fields up to the User constructor
        super(id, name, email, password);
        this.phone = phone;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // Polymorphism: this is the role for every Patient
    @Override
    public String getRole() { return "PATIENT"; }

        // Converts this patient into one line of patients.txt
    public String toFileLine() {
        return getId() + "|" + getName() + "|" + getEmail() + "|" + getPassword() + "|" + phone;
    }
}