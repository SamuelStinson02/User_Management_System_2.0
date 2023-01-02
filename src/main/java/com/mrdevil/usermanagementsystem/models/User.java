package com.mrdevil.usermanagementsystem.models;

public class User extends Person {
    protected User(String userName, String password, String fullName) {
        super(userName, password, fullName, "Usuario");
    }

    protected static User createUser(String userName, String password, String fullName) {
        return new User(userName, password, fullName);
    }

    public static void newUser(String userName, String password, String fullName) {
        Person.addPerson(createUser(userName, password, fullName));
    }
}
