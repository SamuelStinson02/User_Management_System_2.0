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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPersonController implements Initializable {
    private static final Stage stage = new Stage();

    private static Person personLogged;
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
        userTypeBox.setItems(types);

        fullNameTxt.setText(personLogged.getFullName());
        userNameTxt.setText(personLogged.getUserName());
        passTxt.setText(personLogged.getPassword());
        userTypeBox.setValue(personLogged.getUserType());
    }

    public static void display(Person p) throws IOException {
        personLogged = p;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditPersonView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 205);
        stage.setTitle("Editar Persona");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onEditarBtnClicked() {
        String newUserName = userNameTxt.getText();
        String newUserFullName = fullNameTxt.getText();
        String newUserPassword = passTxt.getText();
        String newUserType = userTypeBox.getSelectionModel().getSelectedItem();

        Person.removePerson(personLogged);
        DatabaseController.refreshList(personLogged);

        if (Person.getPersonByUsername(newUserName).getUserName().equals("null")) {
            if (newUserType.equals("Usuario")) {
                User.newUser(newUserName, newUserPassword, newUserFullName);
            } else if (newUserType.equals("Administrador")) {
                Admin.newAdmin(newUserName, newUserPassword, newUserFullName);
            } else {
                AdvancedUser.newAdvancedUser(newUserName, newUserPassword, newUserFullName);
            }
        }
        stage.close();
    }


}
