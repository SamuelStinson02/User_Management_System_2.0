package com.mrdevil.usermanagementsystem.controllers;

import com.mrdevil.usermanagementsystem.Main;
import com.mrdevil.usermanagementsystem.models.Request;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestController implements Initializable {
    private static final Stage stage = new Stage();

    @FXML
    private TableView<Request> requestTable;

    @FXML
    private TableColumn requestCol;

    private final ObservableList<Request> requests = Request.getRequestsDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestTable.setItems(requests);

        requestCol.setCellValueFactory(new PropertyValueFactory("request"));
    }

    public static void display() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RequestView.fxml"));
        Scene scene = new Scene(loader.load(), 400, 200);
        stage.setTitle("Mensajes");
        stage.setScene(scene);
        stage.show();
    }
}
