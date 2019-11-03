package com.unicen.tandilrecicla.data.model;

public class Address {
    private String department;
    private Integer number;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    // Getter Methods

    public String getDepartment() {
        return department;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Setter Methods

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
