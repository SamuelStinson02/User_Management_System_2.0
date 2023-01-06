/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public static final Stage stage = new Stage();
    private static Person personLogged;
    @FXML
    private SplitPane mainContent;
    @FXML
    private ImageView userImg;
    @FXML
    private Button menuBtn, addUserBtn;
    @FXML
    private VBox navList;
    @FXML
    private Label fullNameText;

    public AdminController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUserView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        prepareSlideMenuAnimation();
    }

    private void prepareSlideMenuAnimation() {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), navList);
        menuBtn.setOnAction((ActionEvent evt) -> {
            if (navList.getTranslateX() != 0) {
                openNav.play();
            } else {
                closeNav.setToX(-(navList.getWidth()));
                closeNav.play();
            }
        });
    }

    private void setUserView() throws IOException {
        Image adminImg = new Image(Main.class.getResource("images/adminIcon.png").openStream());
        Image advancedImg = new Image(Main.class.getResource("images/advancedIcon.png").openStream());
        Image normalImg = new Image(Main.class.getResource("images/normalIcon.png").openStream());
        fullNameText.setText(personLogged.getFullName());
        if (personLogged.getUserType().equals("Usuario")) {
            userImg.setImage(normalImg);
        } else if (personLogged.getUserType().equals("Administrador")) {
            userImg.setImage(adminImg);
        } else {
            userImg.setImage(advancedImg);
        }
        mainContent.getItems().set(0, HomeController.display(personLogged).load());
    }

    public static void display(Person person) throws IOException {
        personLogged = person;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdminView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        stage.setTitle("Sistema de Gestión de Usuarios");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onLogoutBtnClicked() throws IOException {
        LoginController.display();
        personLogged = null;
        stage.close();
    }

    @FXML
    protected void onInfoBtnClicked() throws IOException {
        mainContent.getItems().set(0, InfoController.display().load());
    }

    @FXML
    protected void onHomeBtnClicked() throws IOException {
        mainContent.getItems().set(0, HomeController.display(personLogged).load());
    }


    @FXML
    protected void onAddUserBtnClicked() throws IOException {
        NewPersonController.display();
    }

    @FXML
    private void databaseBtnClicked() throws IOException {
        mainContent.getItems().set(0, DatabaseController.display().load());
    }

    @FXML
    private void onSmsBtnClicked() throws IOException {
        RequestController.display();
    }
}
