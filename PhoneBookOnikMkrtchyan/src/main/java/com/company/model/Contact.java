package com.company.model;

import com.company.enums.ContactType;
import com.company.enums.GroupType;

public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private ContactType contactType;
    private GroupType groupType;
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public Address getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}