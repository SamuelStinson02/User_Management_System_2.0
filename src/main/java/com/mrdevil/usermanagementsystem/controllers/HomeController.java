package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private static Person personLogged;
    @FXML
    private ImageView userImgD;
    @FXML
    public Label fullNameTextD, userNameText, usertypeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setHomeView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FXMLLoader display(Person person) {
        personLogged = person;
        return new FXMLLoader(Main.class.getResource("HomeView.fxml"));
    }

    public void setHomeView() throws IOException {
        fullNameTextD.setText("Nombre Completo: " + personLogged.getFullName());
        userNameText.setText("Nombre de Usuario: " + personLogged.getUserName());
        usertypeText.setText("Rol: " + personLogged.getUserType());

        Image adminImg = new Image(Objects.requireNonNull(Main.class.getResource("images/adminIcon.png")).openStream());
        Image advancedImg = new Image(Objects.requireNonNull(Main.class.getResource("images/advancedIcon.png")).openStream());
        Image normalImg = new Image(Objects.requireNonNull(Main.class.getResource("images/normalIcon.png")).openStream());
        if (personLogged.getUserType().equals("Usuario")) {
            userImgD.setImage(normalImg);
        } else if (personLogged.getUserType().equals("Administrador")) {
            userImgD.setImage(adminImg);
        } else {
            userImgD.setImage(advancedImg);
        }
    }
}
