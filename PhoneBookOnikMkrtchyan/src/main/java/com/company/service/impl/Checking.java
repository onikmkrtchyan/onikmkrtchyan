package com.company.service.impl;

import com.company.model.Contact;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * In this class we are checking(filtering) all input parameters,and force user to check it until
 * User write correct:
 */
public class Checking {

    /**
     * Special method that catches exceptions when we input symbols or letters in numbers field(nextInt):
     * If we input letters or symbols this method catches exceptions
     * and force us to write right number until we input right:
     *
     * @return integer number
     */
    public int checkInputInteger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Please insert an integer: not letters or symbols");
            }
        }
    }

    /**
     * Given phone number exist our PhoneBook or not(someone used or not):
     * This method used,that our PhoneBooks phone number be unique:
     *
     * @param phoneNumber given
     * @return true if that phone number exist in PhoneBook
     */
    public boolean uniquePhoneNumber(Set<Contact> contacts, String phoneNumber) {
        for (Contact contact : contacts) {
            if (phoneNumber.equals(contact.getPhoneNumber()))
                return true;
        }
        return false;
    }

    /**
     * Given email address exist our PhoneBook or not(someone used or not):
     * This method used,that our PhoneBooks email address be unique:
     *
     * @param email given
     * @return true, if that email address exist in PhoneBook
     */
    public boolean uniqueEmail(Set<Contact> contacts, String email) {
        for (Contact contact : contacts) {
            if (email.equals(contact.getEmail()))
                return true;
        }
        return false;
    }

    /**
     * method that checks does given email correct or not:
     * must be something@***.**:
     *
     * @param email given email
     * @return correct or not
     */
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * method that checks does given word(name,surname,city) correct or not:
     * must begin with Capital letters and don't contain numbers or symbols:
     *
     * @param inputWord given
     * @return correct word or not
     */
    public boolean isValidInputWord(String inputWord) {

        String namRegExpVar = "[A-Z][a-z]+";

        Pattern pVar = Pattern.compile(namRegExpVar);
        Matcher mVar = pVar.matcher(inputWord);
        return mVar.matches();
    }

    /**
     * method that checks does given two words(name,surname,city) correct or not:
     * must begin with Capital letters and don't contain numbers or symbols:
     *
     * @param inputWord given
     * @return correct or not
     */
    public boolean isValidInputTwoWord(String inputWord) {

        String namRegExpVar = "^[A-Z][a-z]++(?:_" +
                "[A-Z][a-z]+)";
        Pattern pVar = Pattern.compile(namRegExpVar);
        Matcher mVar = pVar.matcher(inputWord);
        return mVar.matches();
    }

    /**
     * method that checks does given number correct or not:
     * number must be 1 to 32.000:
     *
     * @param number given
     * @return correct number or not
     */
    public boolean isValidInputNumber(int number) {
        int MAX_VALUE = 32_000;
        return (number >= 1 && number <= MAX_VALUE);
    }

    /**
     * method that checks does given Armenian phone number correct or not:
     * must begin +374 and contain 8 numbers:
     *
     * @param s given number
     * @return correct Armenian phone number or not
     */
    public boolean isValidPhoneNumber(String s) {
        Pattern p = Pattern.compile("\\+(374)?[1-9]{2}[0-9]{6}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));

    }

    /**
     * method that checks does given code correct or not:
     * must be 1,2,3 or 4:
     *
     * @param code given
     * @return correct code or not
     */
    public boolean isValidInputCode(int code) {
        return (code == 1 || code == 2 || code == 3 || code == 4);
    }

    /**
     * method that checks does given code correct or not:
     * must be 1,2 or 3:
     *
     * @param code given
     * @return correct code or not
     */
    public boolean isValidInputTwoCode(int code) {
        return (code == 1 || code == 2 || code == 3);
    }

}