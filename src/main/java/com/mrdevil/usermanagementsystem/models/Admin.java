package com.mrdevil.usermanagementsystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin extends Person {
    protected Admin(String userName, String password, String fullName) {
        super(userName, password, fullName, "Administrador");
    }

    protected static Admin createAdmin(String userName, String password, String fullName) {
        return new Admin(userName, password, fullName);
    }

    public static void newAdmin(String userName, String password, String fullName) {
        Person.addPerson(createAdmin(userName, password, fullName));
    }
}
