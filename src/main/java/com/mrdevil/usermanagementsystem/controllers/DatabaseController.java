package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button editBtn, deleteBtn;

    private ObservableList<Person> personsDB = Person.getPersons();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTable.setItems(personsDB);

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
    private void onDeleteBtnClicked(){
        Person.removePerson(userTable.getSelectionModel().getSelectedItem());
        userTable.refresh();
    }
}
