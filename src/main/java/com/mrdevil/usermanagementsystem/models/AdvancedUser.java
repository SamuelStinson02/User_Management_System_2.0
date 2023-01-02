package com.mrdevil.usermanagementsystem.models;

public class AdvancedUser extends Person {
    protected AdvancedUser(String userName, String password, String fullName) {
        super(userName, password, fullName, "Usuario Avanzado");
    }

    protected static AdvancedUser createAdvancedUser(String userName, String password, String fullName) {
        return new AdvancedUser(userName, password, fullName);
    }

    public static void newAdvancedUser(String userName, String password, String fullName) {
        Person.addPerson(createAdvancedUser(userName, password, fullName));
    }
}
