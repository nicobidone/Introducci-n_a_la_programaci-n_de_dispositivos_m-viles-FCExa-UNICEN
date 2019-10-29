package com.unicen.tandilrecicla.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zipCode")
    @Expose
    private String zipCode;

    public Address(String department, Integer number, String streetAddress, String city, String state,
                   String zipCode) {
        this.department = department;
        this.number = number;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

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
