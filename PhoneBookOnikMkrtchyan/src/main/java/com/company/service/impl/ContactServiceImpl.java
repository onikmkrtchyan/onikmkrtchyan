package com.company.service.impl;

import com.company.enums.ContactType;
import com.company.enums.GroupType;
import com.company.model.Address;
import com.company.model.Contact;
import com.company.service.ContactService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * In this class we implement all methods,which need for CRUD(Create Read Update Delete) operations:
 */
public class ContactServiceImpl implements ContactService {

    static Set<Contact> contacts = new HashSet<>();
    static Checking checking = new Checking();
    static Scanner scanner = new Scanner(System.in);

    //Printing methods

    /**
     * Method that prints contacts
     *
     * @return contact
     */
    public String toString() {
        StringBuilder contactsToString = null;
        for (Contact contact : contacts)
            if (contactsToString == null)
                contactsToString = new StringBuilder(printContact(contact) + "\n");
            else
                contactsToString.append(printContact(contact)).append("\n");
        if (contactsToString != null) {
            return contactsToString.toString();
        }
        return null;
    }

    /**
     * Method that prints concrete contact
     *
     * @param contacts given Set
     * @return contact
     */
    public String toString(Set<Contact> contacts) {
        StringBuilder contactsToString = null;
        for (Contact contact : contacts)
            if (contactsToString == null)
                contactsToString = new StringBuilder(printContact(contact) + "\n");
            else
                contactsToString.append(printContact(contact)).append("\n");
        if (contactsToString != null) {
            return contactsToString.toString();
        }
        return null;
    }

    /**
     * @param contact contact
     * @return contacts
     */
    public String printContact(Contact contact) {
        return "[First Name = " + contact.getFirstName() +
                ", Last Name = " + contact.getLastName() +
                ", Phone Number = " + contact.getPhoneNumber() +
                ", Email = " + contact.getEmail() +
                ", Contact type = " + contact.getContactType() +
                ", Group type = " + contact.getGroupType() +
                ": Address Contact is: " +
                printAddress(contact.getAddress());
    }

    /**
     * @param address address
     * @return address
     */
    public String printAddress(Address address) {
        return ", Country = " + address.getCountry() +
                ", City = " + address.getCity() +
                ", Street = " + address.getStreet() +
                ", Building = " + address.getBuilding() +
                ", Apartment = " + address.getApartment() +
                "]";

    }

    //Add

    /**
     * method that call create contact method:
     *
     * @return contact add
     */
    public boolean addContact() {
        final Contact contact = createContact();
        return contacts.add(contact);
    }

    //Create

    /**
     * Method that create contact:
     *
     * @return contact
     */
    private static Contact createContact() {
        Contact contact = new Contact();

        System.out.println("Please Enter First name(must begin capital letter and contain only letters)");
        String firstName = scanner.nextLine();
        while (true) {
            if (checking.isValidInputWord(firstName)) {
                contact.setFirstName(firstName);
                break;
            } else {
                System.out.println("Please enter Valid First name(must begin capital letter and contain only letters)");
                firstName = scanner.nextLine();
            }
        }

        System.out.println("Please enter Last name(must begin capital letter and contain only letters)");
        String lastName = scanner.nextLine();
        while (true) {
            if (checking.isValidInputWord(lastName)) {
                contact.setLastName(lastName);
                break;
            } else {
                System.out.println("Please enter Valid last name(must begin capital letter and contain only letters)");
                lastName = scanner.nextLine();
            }
        }

        System.out.println("Please enter Phone number(+374(code)******)");
        String phoneNumber = scanner.nextLine();
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber) && !checking.uniquePhoneNumber(contacts, phoneNumber)) {
                contact.setPhoneNumber(phoneNumber);
                break;
            } else if (checking.isValidPhoneNumber(phoneNumber) && checking.uniquePhoneNumber(contacts, phoneNumber)) {
                System.out.println("Please enter else phone number:This one already used");
                phoneNumber = scanner.nextLine();
            } else {
                System.out.println("Please enter Valid armenian Phone number(+374(code)******)");
                phoneNumber = scanner.nextLine();
            }
        }

        System.out.println("Please enter Email address");
        String email = scanner.nextLine();
        while (true) {
            if (checking.isValidEmail(email) && !checking.uniqueEmail(contacts, email)) {
                contact.setEmail(email);
                break;
            } else if (checking.isValidEmail(email) && checking.uniqueEmail(contacts, email)) {
                System.out.println("Please enter else Email address: this one already used");
                email = scanner.nextLine();
            } else {
                System.out.println("Please enter Valid Email address");
                email = scanner.nextLine();
            }
        }

        System.out.println("Please enter Contact type:(1 for Home)(2 for Mobile)(3 for Work)");
        int contactType = checking.checkInputInteger();
        while (true) {
            if (checking.isValidInputTwoCode(contactType)) {
                switch (contactType) {
                    case 1: {
                        contact.setContactType(ContactType.getByCode(1));
                        break;
                    }
                    case 2:
                        contact.setContactType(ContactType.getByCode(2));
                        break;
                    case 3:
                        contact.setContactType(ContactType.getByCode(3));
                        break;
                }
                break;
            } else {
                System.out.println("Please enter Valid Contact type(1 for Home)(2 for Mobile)(3 for Work)");
                contactType = checking.checkInputInteger();
            }
        }

        System.out.println("Please enter Group type(1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
        int groupType = checking.checkInputInteger();
        while (true) {
            if (checking.isValidInputCode(groupType)) {
                switch (groupType) {
                    case 1:
                        contact.setGroupType(GroupType.getByCode(1));
                        break;
                    case 2:
                        contact.setGroupType(GroupType.getByCode(2));
                        break;
                    case 3:
                        contact.setGroupType(GroupType.getByCode(3));
                        break;
                    case 4:
                        contact.setGroupType(GroupType.getByCode(4));
                        break;
                }
                break;
            } else {
                System.out.println("Please enter Valid Group type" +
                        "(1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
                groupType = checking.checkInputInteger();
            }

        }

        Address address = new Address();
        System.out.println("Contact Address: Please enter Country" +
                "(must begin capital letter and contain only letters)");
        String country = scanner.nextLine();
        while (true) {
            if (checking.isValidInputWord(country)) {
                address.setCountry(country);
                break;
            } else {
                System.out.println("Please enter Valid country name");
                country = scanner.nextLine();
            }
        }

        System.out.println("Contact Address: Please enter City: example New_York" +
                "(must begin capital letters and contain only letters)");
        String city = scanner.nextLine();
        while (true) {
            if (checking.isValidInputWord(city) || checking.isValidInputTwoWord(city)) {
                address.setCity(city);
                break;
            } else {
                System.out.println("Please enter Valid City:example New_York" +
                        "(must begin capital letters and contain only letters)");
                city = scanner.nextLine();
            }
        }

        System.out.println("Contact Address: Please enter Street: example Donald_Trump " +
                "(must begin capital letters and contain only letters)");
        String street = scanner.nextLine();
        while (true) {
            if (checking.isValidInputWord(street) || checking.isValidInputTwoWord(street)) {
                address.setStreet(street);
                break;
            } else {
                System.out.println("Please enter Valid street:example Donald_Trump" +
                        "(must begin capital letters and contain only letters)");
                street = scanner.nextLine();
            }
        }

        System.out.println("Contact Address: Please Enter Building(must contain only numbers)");
        int building = checking.checkInputInteger();
        while (true) {
            if (checking.isValidInputNumber(building)) {
                address.setBuilding(building);
                break;
            } else {
                System.out.println("Please enter Valid Building(must contain only numbers)");
                building = checking.checkInputInteger();
            }
        }

        System.out.println("Contact Address: Please enter Apartment(must contain only numbers)");
        int apartment = checking.checkInputInteger();
        while (true) {
            if (checking.isValidInputNumber(apartment)) {
                address.setApartment(apartment);
                break;
            } else {
                System.out.println("Please enter Valid Apartment(must contain only numbers)");
                apartment = checking.checkInputInteger();
            }
        }

        contact.setAddress(address);
        return contact;
    }

    //Get

    /**
     * Getting contact by phone number
     *
     * @param phoneNumber given
     * @return contact
     */
    public Contact getByPhoneNumber(String phoneNumber) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        return contact;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Getting contact by email address
     *
     * @param email given
     * @return contact
     */
    public Contact getByEmail(String email) {
        while (true) {
            if (checking.isValidEmail(email)) {
                for (Contact contact : contacts) {
                    if (email.equals(contact.getEmail())) {
                        return contact;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Email address");
                email = scanner.nextLine();
            }
        }
    }

    /**
     * Getting contact(s) by first name
     *
     * @param firstName given
     * @return contact(s)
     */
    public Set<Contact> getByFirstName(String firstName) {
        Set<Contact> contactByFirstName = new HashSet<>();
        while (true) {
            if (checking.isValidInputWord(firstName)) {
                for (Contact contact : contacts) {
                    if (firstName.equals(contact.getFirstName())) {
                        contactByFirstName.add(contact);
                        return contactByFirstName;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid First name" +
                        "(must begin capital letters and contain only letters)");
                firstName = scanner.nextLine();
            }
        }
    }

    /**
     * Getting contact(s) by first name and last name
     *
     * @param firstName given
     * @param lastName  given
     * @return contact
     */
    public Set<Contact> getByFirstNameAndLastName(String firstName, String lastName) {
        Set<Contact> contactByFirstNameAndLastName = new HashSet<>();
        while (true) {
            if (checking.isValidInputWord(firstName) && checking.isValidInputWord(lastName)) {
                for (Contact contact : contacts) {
                    if (firstName.equals(contact.getFirstName()) && lastName.equals(contact.getLastName())) {
                        contactByFirstNameAndLastName.add(contact);
                        return contactByFirstNameAndLastName;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid first name and Last name");
                System.out.println("Please enter First name");
                firstName = scanner.nextLine();
                System.out.println("Please enter Last name");
                lastName = scanner.nextLine();
            }
        }
    }

    /**
     * Getting contact(s) by code(GroupType)
     *
     * @param code given
     * @return contact
     */
    public Set<Contact> getByGroupType(int code) {
        Set<Contact> contactByGroupType = new HashSet<>();
        while (true) {
            if (checking.isValidInputCode(code)) {
                for (Contact contact : contacts) {
                    if (code == contact.getGroupType().getCode()) {
                        contactByGroupType.add(contact);
                        return contactByGroupType;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Code" +
                        "(1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
                code = checking.checkInputInteger();
            }
        }
    }

    /**
     * Getting contact(s) by country
     *
     * @param country given
     * @return contact
     */
    public Set<Contact> getByCountry(String country) {
        Set<Contact> contactByCountry = new HashSet<>();
        while (true) {
            if (checking.isValidInputWord(country)) {
                for (Contact contact : contacts) {
                    if (country.equals(contact.getAddress().getCountry())) {
                        contactByCountry.add(contact);
                        return contactByCountry;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Country" +
                        "(must begin capital letter(s) and contain only letters)");
                country = scanner.nextLine();
            }
        }
    }

    /**
     * Getting contact(s) by country city
     *
     * @param city given
     * @return contact
     */
    public Set<Contact> getByCity(String city) {
        Set<Contact> contactByCity = new HashSet<>();
        while (true) {
            if (checking.isValidInputWord(city)) {
                for (Contact contact : contacts) {
                    if (city.equals(contact.getAddress().getCity())) {
                        contactByCity.add(contact);
                        return contactByCity;
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid City" +
                        "(must begin capital letters and contain only letters,if contains two words use '_' symbol)");
                city = scanner.nextLine();
            }
        }
    }

    /**
     * Getting ALL contacts
     *
     * @return contact
     */
    public Set<Contact> getAllContacts() {
        return contacts;
    }

    //Update

    /**
     * Update first name by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param firstName   given must be new first name
     * @return contact
     */
    public Contact updateContactFirstName(String phoneNumber, String firstName) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputWord(firstName)) {
                                contact.setFirstName(firstName);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid First name" +
                                        "(must begin capital letters and contain only letters)");
                                firstName = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update first last by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param lastName    must be new last name
     * @return contact
     */
    public Contact updateContactLastName(String phoneNumber, String lastName) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputWord(lastName)) {
                                contact.setLastName(lastName);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid last name" +
                                        "(must begin capital letters and contain only letters)");
                                lastName = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update email by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param email       must be new email
     * @return contact
     */
    public Contact updateContactEmail(String phoneNumber, String email) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {

                            if (checking.isValidEmail(email) && !checking.uniqueEmail(contacts, email)) {
                                contact.setEmail(email);
                                return contact;

                            } else if (checking.isValidEmail(email) && checking.uniqueEmail(contacts, email)) {
                                System.out.println("Please enter else Email address: this one already used");
                                email = scanner.nextLine();
                            } else {
                                System.out.println("Please enter Valid Email address");
                                email = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update phone number by email(if email is correct and exist in PhoneBook):
     *
     * @param email       given
     * @param phoneNumber must be new phone number
     * @return contact
     */
    public Contact updateContactPhoneNumber(String email, String phoneNumber) {
        while (true) {
            if (checking.isValidEmail(email)) {
                for (Contact contact : contacts) {
                    if (email.equals(contact.getEmail())) {
                        while (true) {
                            if (checking.isValidPhoneNumber(phoneNumber) &&
                                    !checking.uniquePhoneNumber(contacts, phoneNumber)) {
                                contact.setPhoneNumber(phoneNumber);
                                return contact;
                            } else if (checking.isValidPhoneNumber(phoneNumber) &&
                                    checking.uniquePhoneNumber(contacts, phoneNumber)) {
                                System.out.println("Please enter else phone number(+374(code)******)): " +
                                        "this one already used");
                                phoneNumber = scanner.nextLine();
                            } else {
                                System.out.println("Please enter Valid Armenian phone number(+374(code)******))");
                                phoneNumber = scanner.nextLine();
                            }

                        }
                    }
                }
                return null;
            } else {
                System.out.println("Please enter Valid Email address");
                email = scanner.nextLine();
            }
        }
    }

    /**
     * Update code(for contact type) by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param code        must be new code(for contact type)
     * @return contact
     */
    public Contact updateContactsContactType(String phoneNumber, int code) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputTwoCode(code)) {
                                contact.setContactType(ContactType.getByCode(code));
                                return contact;
                            } else {
                                System.out.println("Please enter Valid Code(1 for Home)(2 for Mobile)(3 for Work)");
                                code = checking.checkInputInteger();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update code(for group type) by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param code        must be new code(for group type)
     * @return contact
     */
    public Contact updateContactsGroupType(String phoneNumber, int code) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputCode(code)) {
                                contact.setGroupType(GroupType.getByCode(code));
                                return contact;
                            } else {
                                System.out.println("Please enter Valid code" +
                                        "(1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
                                code = checking.checkInputInteger();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update country by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param country     must be new country
     * @return contact
     */
    public Contact updateContactAddressCountry(String phoneNumber, String country) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputWord(country)) {
                                contact.getAddress().setCountry(country);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid Country" +
                                        "(must begin capital letters and contain only letters)");
                                country = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update city by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param city        must be new city
     * @return contact
     */
    public Contact updateContactAddressCity(String phoneNumber, String city) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputWord(city) || checking.isValidInputTwoWord(city)) {
                                contact.getAddress().setCity(city);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid City" +
                                        "(must begin capital letters and contain only letters)");
                                city = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update street by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param street      must be new street
     * @return contact
     */
    public Contact updateContactAddressStreet(String phoneNumber, String street) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputWord(street) || checking.isValidInputTwoWord(street)) {
                                contact.getAddress().setStreet(street);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid street" +
                                        "(must begin capital letters and contain only letters)");
                                street = scanner.nextLine();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update building by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param building    must be new building
     * @return contact
     */
    public Contact updateContactAddressBuilding(String phoneNumber, int building) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputNumber(building)) {
                                contact.getAddress().setBuilding(building);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid Building(must contain only numbers)");
                                building = checking.checkInputInteger();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Update apartment by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @param apartment   must be new apartment
     * @return contact
     */
    public Contact updateContactAddressApartment(String phoneNumber, int apartment) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        while (true) {
                            if (checking.isValidInputNumber(apartment)) {
                                contact.getAddress().setApartment(apartment);
                                return contact;
                            } else {
                                System.out.println("Please enter Valid Apartment(must contain only numbers)");
                                apartment = checking.checkInputInteger();
                            }
                        }
                    }

                }
                return null;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    //Delete

    /**
     * Delete Contact by phone number(if phone number is correct and exist in PhoneBook):
     *
     * @param phoneNumber given
     * @return true if deleted
     */
    public boolean deleteContactByPhoneNumber(String phoneNumber) {
        while (true) {
            if (checking.isValidPhoneNumber(phoneNumber)) {
                for (Contact contact : contacts) {
                    if (phoneNumber.equals(contact.getPhoneNumber())) {
                        contacts.remove(contact);
                        return true;
                    }

                }
                return false;
            } else {
                System.out.println("Please enter Valid Phone number(+374(code)******))");
                phoneNumber = scanner.nextLine();
            }
        }
    }

    /**
     * Delete Contact by email(if email address is correct and exist in PhoneBook):
     *
     * @param email given
     * @return true if deleted
     */
    public boolean deleteContactByEmail(String email) {
        while (true) {
            if (checking.isValidEmail(email)) {
                for (Contact contact : contacts) {
                    if (email.equals(contact.getEmail())) {
                        contacts.remove(contact);
                        return true;
                    }

                }
                return false;
            } else {
                System.out.println("Please enter Valid Email address");
                email = scanner.nextLine();
            }
        }
    }

    /**
     * Delete ALL contacts
     *
     * @return true if deleted
     */
    public boolean deleteAllContacts() {
        contacts.clear();
        return true;
    }

}