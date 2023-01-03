module com.mrdevil.usermanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphicsEmpty;


    opens com.mrdevil.usermanagementsystem to javafx.fxml;
    opens com.mrdevil.usermanagementsystem.controllers to javafx.fxml;
    opens com.mrdevil.usermanagementsystem.models to javafx.fxml;
    exports com.mrdevil.usermanagementsystem;
    exports com.mrdevil.usermanagementsystem.controllers;
    exports com.mrdevil.usermanagementsystem.models;
}