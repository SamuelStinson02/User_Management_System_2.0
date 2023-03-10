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

public class AdvancedUserController implements Initializable {

    private static final Stage stage = new Stage();
    private static Person personLogged;
    @FXML
    private Label fullNameText;
    @FXML
    private Button menuBtn;
    @FXML
    private VBox navList;
    @FXML
    private ImageView userImg;
    @FXML
    private SplitPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        Image advancedImg = new Image(Main.class.getResource("images/advancedIcon.png").openStream());
        userImg.setImage(advancedImg);
        fullNameText.setText(personLogged.getFullName());
        mainContent.getItems().set(0, HomeController.display(personLogged, stage).load());
    }

    public static void display(Person person) throws IOException {
        personLogged = person;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AdvancedUserView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Sistema de Gesti??n de Usuarios");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onInfoBtnClicked() throws IOException {
        mainContent.getItems().set(0, InfoController.display().load());
    }

    @FXML
    protected void onHomeBtnClicked() throws IOException {
        mainContent.getItems().set(0, HomeController.display(personLogged, stage).load());
    }

    @FXML
    private void databaseBtnClicked() throws IOException {
        mainContent.getItems().set(0, DatabaseAUController.display().load());
    }

    @FXML
    protected void onLogoutBtnClicked() throws IOException {
        LoginController.display();
        personLogged = null;
        stage.close();
    }

    public static Person getPersonLogged() {
        return personLogged;
    }
}
