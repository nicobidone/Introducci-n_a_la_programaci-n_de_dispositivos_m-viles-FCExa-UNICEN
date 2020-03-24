package com.unicen.tandilrecicla.data.model;

public class RegisteredUserBuilder {

    static public RegisteredUser getEmptyUser() {
        Address address = new Address();
        address.setDepartment("");
        address.setCity("");
        address.setNumber(-1);
        address.setStreetAddress("");
        address.setCity("");
        address.setState("");
        address.setZipCode("");
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setFirstName("");
        registeredUser.setLastName("");
        registeredUser.setMail("");
        registeredUser.setUsername("");
        registeredUser.setAddress(address);
        return registeredUser;
    }
}
