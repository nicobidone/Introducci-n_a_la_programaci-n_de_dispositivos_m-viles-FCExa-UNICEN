package com.unicen.tandilrecicla.data.model;

public class RegisteredUser {

    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private Address address;

    // Getter Methods

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    // Setter Methods

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(Address addressObject) {
        this.address = addressObject;
    }
}

