package com.unicen.tandilrecicla.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisteredUser {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("username")
    @Expose
    private String username;
//    @SerializedName("address")
//    @Expose
//    private Address address;

    public RegisteredUser(String firstName, String lastName, String mail, String username, Address addressObject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
//        this.address = addressObject;
    }

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

//    public Address getAddress() {
//        return address;
//    }

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

//    public void setAddress(Address addressObject) {
//        this.address = addressObject;
//    }
}

