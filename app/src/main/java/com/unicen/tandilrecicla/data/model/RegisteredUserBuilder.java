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

    static public RegisteredUser getRegisteredUser() {
        Address address = new Address();
        address.setDepartment("Tandil");
        address.setCity("Tandil");
        address.setNumber(874);
        address.setStreetAddress("Alberdi");
        address.setCity("Tandil");
        address.setState("Buenos Aires");
        address.setZipCode("7000");
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setFirstName("Mauri");
        registeredUser.setLastName("Arroqui");
        registeredUser.setMail("mauriarroqui@gmail.com");
        registeredUser.setUsername("nicob");
        registeredUser.setAddress(address);
        return registeredUser;
    }
}
