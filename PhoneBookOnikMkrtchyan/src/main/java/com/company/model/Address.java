package com.company.model;

public class Address {

    private String country;
    private String city;
    private String street;
    private int building;
    private int apartment;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public int getApartment() {
        return apartment;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }
}