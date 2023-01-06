package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseController implements Initializable {

    @FXML
    private TableView<Person> userTable;

    @FXML
    private TableColumn userNameCol, userPassCol, userFullNameCol, userTypeCol;

    @FXML
    private Button editBtn, deleteBtn, filterBtn;
    @FXML
    private ChoiceBox<String> filterBox;

    private final ObservableList<Person> personsDB = Person.getPersons();
    private static ObservableList<Person> adminsDB = FXCollections.observableArrayList();
    private static ObservableList<Person> advancedUsersDB = FXCollections.observableArrayList();
    private static ObservableList<Person> usersDB = FXCollections.observableArrayList();
    private ObservableList<String> filters = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTable.setItems(personsDB);

        filters.addAll("Todos", "Usuarios", "Usuarios Avanzados", "Administradores");
        filterBox.setValue("Todos");
        filterBox.setItems(filters);

        userFullNameCol.setCellValueFactory(new PropertyValueFactory("fullName"));
        userNameCol.setCellValueFactory(new PropertyValueFactory("userName"));
        userPassCol.setCellValueFactory(new PropertyValueFactory("password"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory("userType"));
    }

    public static FXMLLoader display() {
        return new FXMLLoader(Main.class.getResource("DatabaseView.fxml"));
    }

    @FXML
    private void onEditBtnClicked() throws IOException {
        EditPersonController.display(userTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onDeleteBtnClicked() {
        personsDB.remove(userTable.getSelectionModel().getSelectedItem());
        refreshList(userTable.getSelectionModel().getSelectedItem());
       // Person.removePerson(userTable.getSelectionModel().getSelectedItem());
        userTable.refresh();
    }

    @FXML
    private void onFilterBtnClicked() {
        String filter = filterBox.getSelectionModel().getSelectedItem();

        for (Person p : personsDB) {
            if (p.getUserType().equals("Usuario")) {
                if (!usersDB.contains(p)) usersDB.add(p);
            } else if (p.getUserType().equals("Administrador")) {
                if (!adminsDB.contains(p)) adminsDB.add(p);
            } else {
                if (!advancedUsersDB.contains(p)) advancedUsersDB.add(p);
            }
        }

        if (filter.equals("Todos")) {
            userTable.setItems(personsDB);
        } else if (filter.equals("Usuarios")) {
            userTable.setItems(usersDB);
        } else if (filter.equals("Administradores")) {
            userTable.setItems(adminsDB);
        } else {
            userTable.setItems(advancedUsersDB);
        }

        userTable.refresh();
    }

    public static void refreshList(Person p) {
        String userType = p.getUserType();

        if (userType.equals("Usuario")) usersDB.remove(p);
        else if (userType.equals("Administrador")) adminsDB.remove(p);
        else advancedUsersDB.remove(p);
    }

    public static void updateList(Person p){
        String userType = p.getUserType();

        if (userType.equals("Usuario")) usersDB.add(p);
        else if (userType.equals("Administrador")) adminsDB.add(p);
        else advancedUsersDB.add(p);
    }
}
