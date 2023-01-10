package com.mrdevil.usermanagementsystem;

import com.mrdevil.usermanagementsystem.controllers.LoginController;
import com.mrdevil.usermanagementsystem.models.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        //Estado inicial de programa
        Admin.newAdmin("admin", "admin", "admin");
        AdvancedUser.newAdvancedUser("osmel", "123", "Osmel Profe");
        User.newUser("samuel", "123", "Samuel Salazar");

        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        LoginController.display();
    }
}
