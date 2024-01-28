package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
//import java.util.prefs.Preferences;

import java.io.File;

public class FileDirectoryChooser /*extends Application*/ {
    /*
    private Preferences preferences;

    private TextField saveFilePathTextField;
    private TextField defaultSaveDirectoryTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Directory Chooser");

        // Инициализируем настройки
        preferences = Preferences.userNodeForPackage(FileDirectoryChooser.class);

        // Создаем компоненты GUI
        saveFilePathTextField = new TextField();
        saveFilePathTextField.setPromptText("Select a directory for saving a file...");
        saveFilePathTextField.setEditable(false);

        Button chooseSaveDirectoryButton = new Button("Choose Save Directory");
        chooseSaveDirectoryButton.setOnAction(e -> chooseSaveDirectory());

        defaultSaveDirectoryTextField = new TextField(preferences.get("defaultSaveDirectory", ""));
        defaultSaveDirectoryTextField.setPromptText("Enter default save directory...");

        Button setDefaultDirectoryButton = new Button("Set Default Save Directory");
        setDefaultDirectoryButton.setOnAction(e -> setDefaultDirectory());

        // Создаем компоновку GUI
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Choose directory for saving a file:"),
                saveFilePathTextField,
                chooseSaveDirectoryButton,
                new Label("Set default save directory:"),
                defaultSaveDirectoryTextField,
                setDefaultDirectoryButton
        );

        Scene scene = new Scene(layout, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void chooseSaveDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Save Directory");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            saveFilePathTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void setDefaultDirectory() {
        String defaultDirectory = defaultSaveDirectoryTextField.getText();

        if (!defaultDirectory.isEmpty()) {
            preferences.put("defaultSaveDirectory", defaultDirectory);
            System.out.println("Default save directory set to: " + defaultDirectory);
        } else {
            System.out.println("Please enter a valid default save directory.");
        }
    }*/

}
