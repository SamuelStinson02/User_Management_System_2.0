package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Admin;
import com.mrdevil.usermanagementsystem.models.AdvancedUser;
import com.mrdevil.usermanagementsystem.models.Person;
import com.mrdevil.usermanagementsystem.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewPersonController implements Initializable {

    private static final Stage stage = new Stage();

    @FXML
    private TextField fullNameTxt, userNameTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private ChoiceBox<String> userTypeBox;

    private final ObservableList<String> types = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        types.add("Usuario");
        types.add("Usuario Avanzado");
        types.add("Administrador");

        userTypeBox.setValue("Usuario");
        userTypeBox.setItems(types);
    }

    public static void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("NewPersonView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 205);
        stage.setTitle("Crear Nueva Persona");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onCrearBtnClicked() {
        String userName = userNameTxt.getText();
        String fullName = fullNameTxt.getText();
        String userPass = passTxt.getText();
        String userType = userTypeBox.getSelectionModel().getSelectedItem();

        if (Person.getPersonByUsername(userName).getUserName().equals("null")) {
            if (userType.equals("Usuario")) {
                User.newUser(userName, userPass, fullName);
            } else if (userType.equals("Administrador")) {
                Admin.newAdmin(userName, userPass, fullName);
            } else {
                AdvancedUser.newAdvancedUser(userName, userPass, fullName);
            }
        }
        stage.close();
    }
}
