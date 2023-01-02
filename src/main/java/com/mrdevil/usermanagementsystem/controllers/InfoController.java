package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static FXMLLoader display() {
        return new FXMLLoader(Main.class.getResource("InfoView.fxml"));
    }
}
