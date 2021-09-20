package com.company.service;

import com.company.model.Contact;

import java.util.Set;

public interface ContactService {
    //Add
    boolean addContact();

    //Get
    Contact getByPhoneNumber(String phoneNumber);

    Contact getByEmail(String email);

    Set<Contact> getByFirstName(String firstName);

    Set<Contact> getByFirstNameAndLastName(String firstName, String lastName);

    Set<Contact> getByGroupType(int code);

    Set<Contact> getByCountry(String country);

    Set<Contact> getByCity(String city);

    Set<Contact> getAllContacts();

    //Update
    Contact updateContactFirstName(String phoneNumber, String firstName);

    Contact updateContactLastName(String phoneNumber, String lastName);

    Contact updateContactEmail(String phoneNumber, String email);

    Contact updateContactPhoneNumber(String email, String phoneNumber);

    Contact updateContactsContactType(String phoneNumber, int index);

    Contact updateContactsGroupType(String phoneNumber, int index);

    Contact updateContactAddressCountry(String phoneNumber, String country);

    Contact updateContactAddressCity(String phoneNumber, String city);

    Contact updateContactAddressStreet(String phoneNumber, String street);

    Contact updateContactAddressBuilding(String phoneNumber, int building);

    Contact updateContactAddressApartment(String phoneNumber, int apartment);

    //Delete
    boolean deleteContactByPhoneNumber(String phoneNumber);

    boolean deleteContactByEmail(String email);

    boolean deleteAllContacts();
}