package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class LoginController {
    private static final Stage stage = new Stage();

    @FXML
    private ImageView img;

    @FXML
    private TextField userText;


    @FXML
    private PasswordField passText;

    @FXML
    private Button loginBtn, saveStateBtn, loadStateBtn;

    public static void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        stage.setScene(scene);


        stage.setTitle("Sistema de Gestión de Usuarios");
        stage.setResizable(false);
        stage.show();
    }

    private void close() {
        stage.close();
    }

    @FXML
    private void onLoginBtnClicked() throws IOException {
        if (!userText.getText().isEmpty() && !passText.getText().isEmpty()) {
            String userName = userText.getText();
            String userPass = passText.getText();

            Person p = Person.getPersonByUsername(userName);
            if (!p.getUserName().equals("null")) {
                if (!p.getMessage().equals("null")) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText(null);
                    a.setTitle("Información");
                    a.setContentText(p.getMessage());
                    a.showAndWait();
                    p.setMessage("null");
                }
                if (p.getPassword().equals(userPass)) {
                    if (p.getUserType().equals("Usuario")) {
                        UserController.display(Person.getPersonByUsername(userName));
                        close();
                    } else if (p.getUserType().equals("Administrador")) {
                        AdminController.display(Person.getPersonByUsername(userName));
                        close();
                    } else {
                        AdvancedUserController.display(Person.getPersonByUsername(userName));
                        close();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setTitle("Registro Inválido");
                    a.setContentText("Contraseña incorrecto");
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

    public void onKeyEnter(KeyEvent enterEvent) throws IOException {
        if (enterEvent.getCode() == KeyCode.ENTER) {
            onLoginBtnClicked();
        }
    }

    public void onKeyAltF4(KeyEvent altF4Event) {
        if (altF4Event.getCode() == KeyCode.TAB) {
            stage.close();
        }
    }

    @FXML
    private void onSaveStateBtnClicked() {
        ArrayList<Person> persons = new ArrayList<>();
        for (Person p : Person.getPersons()) persons.add(p);

        try (FileOutputStream fos = new FileOutputStream("programState"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(persons);

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setTitle("Hecho");
            a.setContentText("Estado guardado correctamente");
            a.showAndWait();

        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setTitle("Error");
            a.setContentText("No se pudo guardar el estado del programa");
            a.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void onLoadStateBtnClicked() {
        ArrayList<Person> persons = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("programState"); ObjectInputStream ois = new ObjectInputStream(fis)) {

            persons = (ArrayList) ois.readObject();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setTitle("Hecho");
            a.setContentText("Estado cargado correctamente");
            a.showAndWait();


        } catch (IOException | ClassNotFoundException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setTitle("Error");
            a.setContentText("No se pudo cargar el estado del programa");
            a.showAndWait();
            e.printStackTrace();
        }

        for (Person p : persons) {
            if (Person.getPersonByUsername(p.getUserName()).getUserName().equals("null")) Person.addPerson(p);
        }
    }
}
