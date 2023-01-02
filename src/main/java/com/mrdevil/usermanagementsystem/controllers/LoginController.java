package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static final Stage stage = new Stage();
    @FXML
    private ImageView img;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passText;

    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Sistema de Gestión de Usuarios");
        stage.show();
    }

    public void close() {
        stage.close();
    }

    @FXML
    private void onLoginBtnClicked() throws IOException {
        if (!userText.getText().isEmpty() && !passText.getText().isEmpty()) {
            String userName = userText.getText();
            String userPass = passText.getText();

            Person p = Person.getPersonByUsername(userName);
            if (!p.getUserName().equals("null")) {
                if (p.getPassword().equals(userPass)) {
                    if (p.getUserType().equals("Usuario")){
                        UserController.display(Person.getPersonByUsername(userName));
                        close();
                    } else if (p.getUserType().equals("Administrador")) {
                        AdminController.display(Person.getPersonByUsername(userName));
                        close();
                    }else {
                        AdvancedUserController.display(Person.getPersonByUsername(userName));
                        close();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setTitle("Registro Inválido");
                    a.setContentText("Usuario o contraseña incorrecto");
                    a.showAndWait();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Registro Inválido");
                a.setContentText("Usuario " + userName + " no existe. Porfavor contacte a un administrador.");
                a.showAndWait();
            }
            userText.setText("");
            passText.setText("");
        }
    }
}
