package com.mrdevil.usermanagementsystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Person implements Serializable {
    private String userName, password, fullName, userType, message = "null";

    protected static ObservableList<Person> personsDB = FXCollections.observableArrayList();

    public Person(String userName, String password, String fullName, String userType) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void addPerson(Person p) {
        personsDB.add(p);
    }

    public static ObservableList<Person> getPersons() {
        return personsDB;
    }

    public static Person getPersonByUsername(String userName) {
        for (Person p : personsDB) {
            if (p.getUserName().equals(userName)) return p;
        }
        return new Person("null", "null", "null", "null");
    }

    public static void removePerson(Person p) {
        personsDB.remove(p);
    }

    @Override
    public String toString() {
        return "Person [username=" + userName + ", password=" + password +
                ", fullname=" + fullName + ", usertype=" + userType + "]";
    }
}
