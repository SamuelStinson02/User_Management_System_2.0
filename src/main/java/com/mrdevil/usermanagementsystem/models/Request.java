package com.mrdevil.usermanagementsystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Request {
    private String request;

    private static ObservableList<Request> requestsDB = FXCollections.observableArrayList();

    public Request(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public static void addRequest(Request r){
        requestsDB.add(r);
    }

    public static ObservableList<Request> getRequestsDB(){
        return requestsDB;
    }
}
