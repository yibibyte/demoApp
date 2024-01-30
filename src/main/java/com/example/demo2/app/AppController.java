package com.example.demo2.app;

/**
 * Sample Skeleton for 'app.fxml' Controller Class
 */

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.LocalDateTimePicker;

public class AppController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultDataTime.setLocalDateTime(LocalDateTime.now());
    }

    public void handleStartScript(ActionEvent actionEvent) {
//        startScript.setOnAction(event -> );
    }

    public void handleSelectDateTime(MouseEvent mouseEvent) {
    }

    public void handleChooseFileCopy(ActionEvent actionEvent) {
    }

    public void handleChooseDirectory(ActionEvent actionEvent) {
    }

    public void handleDeleteFileCopy(ActionEvent actionEvent) {
    }

    public void handleDefaultDataTime(MouseEvent mouseEvent) {
    }

    public void handleDefaultPathFolder(ActionEvent actionEvent) {
    }

    public void handlePathFolder(ActionEvent actionEvent) {
    }

    public void typeNameBackupCopy(ActionEvent actionEvent) {
    }
}
