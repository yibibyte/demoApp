package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalDateTimePicker;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chooseDirectory"
    private Button chooseDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="chooseFileCopy"
    private Button chooseFileCopy; // Value injected by FXMLLoader

    @FXML // fx:id="defaultDataTime"
    private LocalDateTimePicker defaultDataTime; // Value injected by FXMLLoader

    @FXML // fx:id="defaultPathFolder"
    private CheckBox defaultPathFolder; // Value injected by FXMLLoader

    @FXML // fx:id="deleteFileCopy"
    private Button deleteFileCopy; // Value injected by FXMLLoader

    @FXML // fx:id="nameBackupCopy"
    private TextField nameBackupCopy; // Value injected by FXMLLoader

    @FXML // fx:id="pathFolder"
    private TextField pathFolder; // Value injected by FXMLLoader

    @FXML // fx:id="selectDateTime"
    private LocalDateTimePicker selectDateTime; // Value injected by FXMLLoader

    @FXML // fx:id="startScript"
    private Button startScript; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chooseDirectory != null : "fx:id=\"chooseDirectory\" was not injected: check your FXML file 'app.fxml'.";
        assert chooseFileCopy != null : "fx:id=\"chooseFileCopy\" was not injected: check your FXML file 'app.fxml'.";
        assert defaultDataTime != null : "fx:id=\"defaultDataTime\" was not injected: check your FXML file 'app.fxml'.";
        assert defaultPathFolder != null : "fx:id=\"defaultPathFolder\" was not injected: check your FXML file 'app.fxml'.";
        assert deleteFileCopy != null : "fx:id=\"deleteFileCopy\" was not injected: check your FXML file 'app.fxml'.";
        assert nameBackupCopy != null : "fx:id=\"nameBackupCopy\" was not injected: check your FXML file 'app.fxml'.";
        assert pathFolder != null : "fx:id=\"pathFolder\" was not injected: check your FXML file 'app.fxml'.";
        assert selectDateTime != null : "fx:id=\"selectDateTime\" was not injected: check your FXML file 'app.fxml'.";
        assert startScript != null : "fx:id=\"startScript\" was not injected: check your FXML file 'app.fxml'.";

    }

}