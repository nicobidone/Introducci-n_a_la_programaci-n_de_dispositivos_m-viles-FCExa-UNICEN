package com.unicen.tandilrecicla.data.model;

public class Utils {
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

    static public Recycling getRecycling() {
        Recycling recycling = new Recycling();
        recycling.setBottles(1);
        recycling.setTetrabriks(5);
        recycling.setGlass(3);
        recycling.setPaperboard(4);
        recycling.setCans(2);
        recycling.setDate("2018-11-29");
        return recycling;
    }
}
