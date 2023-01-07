package com.mrdevil.usermanagementsystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Request {
    private String request, newPassword;
    private Person personLogged;

    private static final ObservableList<Request> requestsDB = FXCollections.observableArrayList();

    public Request(String request, String newPassword, Person personLogged) {
        this.request = request;
        this.newPassword = newPassword;
        this.personLogged = personLogged;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Person getPersonLogged() {
        return personLogged;
    }

    public void setPersonLogged(Person personLogged) {
        this.personLogged = personLogged;
    }

    public static void addRequest(Request r) {
        requestsDB.add(r);
    }

    public static ObservableList<Request> getRequestsDB() {
        return requestsDB;
    }
}
