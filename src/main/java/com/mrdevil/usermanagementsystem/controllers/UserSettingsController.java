package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import com.mrdevil.usermanagementsystem.models.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController implements Initializable {

    private static Person personLogged;
    @FXML
    private Button enviarBtn;
    @FXML
    private PasswordField oldPassText, newPassText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static FXMLLoader display(Person person) {
        personLogged = person;
        return new FXMLLoader(Main.class.getResource("UserSettingsView.fxml"));
    }

    @FXML
    private void onEnviarBtnClicked() {
        if (!oldPassText.getText().isEmpty() && !newPassText.getText().isEmpty()) {
            if (personLogged.getPassword().equals(oldPassText.getText())) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setTitle("Cambio de Contraseña");
                a.setContentText("La solicitud fue enviada a un administrador");
                a.show();
                Request.addRequest(new Request("Usuario: " + personLogged.getUserName() + " solicita " +
                        "cambiar su contraseña a: " + newPassText.getText()));
                oldPassText.setText("");
                newPassText.setText("");
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Cambio de Contraseña");
                a.setContentText("Antigua contraseña erronea");
                a.show();
            }
        }
    }
}
