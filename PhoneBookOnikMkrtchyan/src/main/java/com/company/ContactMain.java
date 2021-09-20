package com.company;

import com.company.model.Contact;
import com.company.service.impl.Checking;
import com.company.service.impl.ContactServiceImpl;

import java.util.Scanner;
import java.util.Set;

class ContactsMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactServiceImpl service = new ContactServiceImpl();
        Checking checking = new Checking();
        System.out.println("Welcome to PhoneBook: Please follow to Commands");

        while (true) {
            System.out.println("Please insert 1 for Adding contact");
            System.out.println("Please insert 2 for Getting contact");
            System.out.println("Please insert 3 for Updating contact");
            System.out.println("Please insert 4 for Deleting contact");
            System.out.println("Please insert 0 to Close PhoneBook");

            int command = checking.checkInputInteger();
            if (command == 0) {
                System.out.println("Your PhoneBook has been Closed successful");
                break;
            }
            switch (command) {
                case 1: {
                    final boolean isAdded = service.addContact();
                    if (isAdded)
                        System.out.println("Your contact successfully added in your PhoneBook");
                    break;
                }
                case 2: {
                    System.out.println("Please insert 1 for get contact(s) by FirstName");
                    System.out.println("Please insert 2 for get contact(s) by FirstName and LastName");
                    System.out.println("Please insert 3 for get contact by Phone number");
                    System.out.println("Please insert 4 for get contact by Email address");
                    System.out.println("Please insert 5 for get contact(s) by Group type ");
                    System.out.println("Please insert 6 for get contact(s) by Country ");
                    System.out.println("Please insert 7 for get contact(s) by City");
                    System.out.println("Please insert 8 for get ALL contacts");
                    System.out.println("Please insert 0 for exit main Menu");
                    int innerCommand = checking.checkInputInteger();
                    switch (innerCommand) {
                        case 1: {
                            System.out.println("Please enter First name");
                            final Set<Contact> contactByFirstName = service.getByFirstName(scanner.nextLine());
                            if (contactByFirstName != null)
                                System.out.println(service.toString(contactByFirstName));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 2: {
                            System.out.println("Please enter First name");
                            String firstName = scanner.nextLine();
                            System.out.println("Please enter Last name");
                            String lastName = scanner.nextLine();
                            final Set<Contact> contactByFirstNameAndLastName = service.getByFirstNameAndLastName(firstName, lastName);
                            if (contactByFirstNameAndLastName != null)
                                System.out.println(service.toString(contactByFirstNameAndLastName));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 3: {
                            System.out.println("Please enter Phone number(+374(code)******)");
                            final Contact contactByPhoneNumber = service.getByPhoneNumber(scanner.nextLine());
                            if (contactByPhoneNumber != null)
                                System.out.println(service.printContact(contactByPhoneNumber));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 4: {
                            System.out.println("Please enter Email address");
                            final Contact contactByEmail = service.getByEmail(scanner.nextLine());
                            if (contactByEmail != null)
                                System.out.println(service.printContact(contactByEmail));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 5: {
                            System.out.println("Please enter Group type code:" +
                                    " (1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
                            final Set<Contact> contactByGroupType = service.getByGroupType(checking.checkInputInteger());
                            if (contactByGroupType != null)
                                System.out.println(service.toString(contactByGroupType));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 6: {
                            System.out.println("Please enter the Country(Must begin with capital letters)");
                            final Set<Contact> contactByCountry = service.getByCountry(scanner.nextLine());
                            if (contactByCountry != null)
                                System.out.println(service.toString(contactByCountry));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 7: {
                            System.out.println("Please enter City(Must begin with capital letters)");
                            final Set<Contact> contactByCity = service.getByCity(scanner.nextLine());
                            if (contactByCity != null)
                                System.out.println(service.toString(contactByCity));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 8: {
                            if (service.getAllContacts().isEmpty())
                                System.out.println("Your PhoneBook is Empty");
                            else
                                System.out.println(service);
                            break;
                        }
                        case 0:
                            break;
                    }
                    break;
                }
                case 3: {
                    System.out.println("Please insert 1 for update contact FirstName");
                    System.out.println("Please insert 2 for update contact LastName");
                    System.out.println("Please insert 3 for update contact Email address");
                    System.out.println("Please insert 4 for update contact Contact Type");
                    System.out.println("Please insert 5 for update contact Group Type");
                    System.out.println("Please insert 6 for update contact Address country");
                    System.out.println("Please insert 7 for update contact Address city");
                    System.out.println("Please insert 8 for update contact Address street");
                    System.out.println("Please insert 9 for update contact Address Building");
                    System.out.println("Please insert 10 for update contact Address Apartment");
                    System.out.println("Please insert 11 for update contact Phone number");
                    System.out.println("Please insert 0 for exit Menu");
                    int innerCommand = checking.checkInputInteger();
                    switch (innerCommand) {
                        case 1: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new First name");
                            String newFirstName = scanner.nextLine();
                            final Contact updateContactFirstName =
                                    service.updateContactFirstName(phoneNumber, newFirstName);
                            if (updateContactFirstName != null)
                                System.out.println(service.printContact(updateContactFirstName));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 2: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Last name");
                            String newLastName = scanner.nextLine();
                            final Contact updateContactLastName =
                                    service.updateContactLastName(phoneNumber, newLastName);
                            if (updateContactLastName != null)
                                System.out.println(service.printContact(updateContactLastName));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 3: {
                            System.out.println("Please enter Phone Number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Email address");
                            String newEmail = scanner.nextLine();
                            final Contact updateContactEmail = service.updateContactEmail(phoneNumber, newEmail);
                            if (updateContactEmail != null)
                                System.out.println(service.printContact(updateContactEmail));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 4: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new contact type Code" +
                                    "(1 for Home)(2 for Mobile)(3 for Work)");
                            int newContactsContactType = checking.checkInputInteger();
                            final Contact updateContactsContactType =
                                    service.updateContactsContactType(phoneNumber, newContactsContactType);
                            if (updateContactsContactType != null)
                                System.out.println(service.printContact(updateContactsContactType));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 5: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Group type Code" +
                                    "(1 for Default)(2 for Friends)(3 for CoWorker)(4 for Family)");
                            int newContactsGroupType = checking.checkInputInteger();
                            final Contact updateContactsGroupType =
                                    service.updateContactsGroupType(phoneNumber, newContactsGroupType);
                            if (updateContactsGroupType != null)
                                System.out.println(service.printContact(updateContactsGroupType));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 6: {
                            System.out.println("Please enter the Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Country");
                            String newCountry = scanner.nextLine();
                            final Contact updateContactAddressCountry =
                                    service.updateContactAddressCountry(phoneNumber, newCountry);
                            if (updateContactAddressCountry != null)
                                System.out.println(service.printContact(updateContactAddressCountry));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 7: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new City");
                            String newCity = scanner.nextLine();
                            final Contact updateContactAddressCity =
                                    service.updateContactAddressCity(phoneNumber, newCity);
                            if (updateContactAddressCity != null)
                                System.out.println(service.printContact(updateContactAddressCity));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 8: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Street");
                            String newStreet = scanner.nextLine();
                            final Contact updateContactAddressStreet =
                                    service.updateContactAddressStreet(phoneNumber, newStreet);
                            if (updateContactAddressStreet != null)
                                System.out.println(service.printContact(updateContactAddressStreet));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 9: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Building");
                            int newBuilding = checking.checkInputInteger();
                            final Contact updateContactAddressBuilding =
                                    service.updateContactAddressBuilding(phoneNumber, newBuilding);
                            if (updateContactAddressBuilding != null)
                                System.out.println(service.printContact(updateContactAddressBuilding));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 10: {
                            System.out.println("Please enter Phone number");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Please enter new Apartment");
                            int newApartment = checking.checkInputInteger();
                            final Contact updateContactAddressApartment =
                                    service.updateContactAddressApartment(phoneNumber, newApartment);
                            if (updateContactAddressApartment != null)
                                System.out.println(service.printContact(updateContactAddressApartment));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 11: {
                            System.out.println("Please enter Email address");
                            String email = scanner.nextLine();
                            System.out.println("Please enter new Phone number");
                            String newPhoneNumber = scanner.nextLine();
                            final Contact updateContactPhoneNumber =
                                    service.updateContactPhoneNumber(email, newPhoneNumber);
                            if (updateContactPhoneNumber != null)
                                System.out.println(service.printContact(updateContactPhoneNumber));
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 0: {
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Please insert 1 for delete contact by Phone number");
                    System.out.println("Please insert 2 for delete contact by Email address");
                    System.out.println("Please insert 3 for delete ALL contacts");
                    System.out.println("Please insert 0 for exit Menu");

                    int innerCommand = checking.checkInputInteger();
                    switch (innerCommand) {
                        case 1: {
                            System.out.println("Please enter Phone number(+374(code)******)");
                            final boolean isDelete = service.deleteContactByPhoneNumber(scanner.nextLine());
                            if (isDelete)
                                System.out.println("You successfully  Deleted contact");
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 2: {
                            System.out.println("Please enter Email address");
                            final boolean isDelete = service.deleteContactByEmail(scanner.nextLine());
                            if (isDelete)
                                System.out.println("You successfully  Deleted contact");
                            else
                                System.out.println("Sorry that contact does not exist in your PhoneBook");
                            break;
                        }
                        case 3: {
                            final boolean isDelete = service.deleteAllContacts();
                            if (isDelete)
                                System.out.println("You successfully  Deleted  all contacts");

                            break;
                        }
                        case 0:
                            break;
                    }
                    break;
                }
                default:
                    System.out.println("Please insert Valid Command");
            }
        }
    }
}