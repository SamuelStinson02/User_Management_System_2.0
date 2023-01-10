package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private static Person personLogged;
    private static Stage thisStage;
    @FXML
    private ImageView userImgD;
    @FXML
    public Label fullNameTextD, userNameText, usertypeText;
    @FXML
    private Button saveUserDataBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setHomeView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FXMLLoader display(Person person, Stage stage) {
        personLogged = person;
        thisStage = stage;
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

    @FXML
    private void onSaveUserDataBtnClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar informe de usuario");
        fileChooser.setInitialFileName(personLogged.getFullName());
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", ".txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showSaveDialog(thisStage);

        if (file != null) {
            FileWriter fw;
            BufferedWriter bw = null;

            try {
                fw = new FileWriter(file, false);
                bw = new BufferedWriter(fw);

                String txt = "Nombre completo: " + personLogged.getFullName() + "\n" +
                        "Nombre de usuario: " + personLogged.getUserName() + "\n" +
                        "Contraseña: " + personLogged.getPassword() + "\n" +
                        "Rol: " + personLogged.getUserType();
                bw.write(txt, 0, txt.length());

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setTitle("Hecho");
                a.setContentText("Informe guardado correctamente");
                a.showAndWait();

            } catch (IOException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Error");
                a.setContentText("Informe no se ha podido guardar");
                a.showAndWait();
                e.printStackTrace();
            } finally {
                try {
                    assert bw != null;
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
