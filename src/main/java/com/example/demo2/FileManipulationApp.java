package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManipulationApp extends Application {

    private File selectedFile;
    private TextField saveDirectoryTextField;
    private TextField defaultSaveDirectoryTextField;
    private TextField newFileNameTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Manipulation App");

        saveDirectoryTextField = new TextField();
        saveDirectoryTextField.setPromptText("Select a directory for saving a file...");
        saveDirectoryTextField.setEditable(false);

        Button chooseSaveDirectoryButton = new Button("Choose Save Directory");
        chooseSaveDirectoryButton.setOnAction(e -> chooseSaveDirectory());

        defaultSaveDirectoryTextField = new TextField();
        defaultSaveDirectoryTextField.setPromptText("Enter default save directory...");

        newFileNameTextField = new TextField();
        newFileNameTextField.setPromptText("Enter new file name...");

        Button renameButton = new Button("Rename File");
        renameButton.setOnAction(e -> renameFile());

        Button deleteButton = new Button("Delete File");
        deleteButton.setOnAction(e -> deleteFile());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Choose directory for saving a file:"),
                saveDirectoryTextField,
                chooseSaveDirectoryButton,
                new Label("Set default save directory:"),
                defaultSaveDirectoryTextField,
                new Label("Enter new file name:"),
                newFileNameTextField,
                renameButton,
                deleteButton
        );

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void chooseSaveDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Save Directory");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            saveDirectoryTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void renameFile() {
        String newFileName = newFileNameTextField.getText();
        String saveDirectory = saveDirectoryTextField.getText();

        if (selectedFile != null && selectedFile.exists() && !newFileName.isEmpty() && !saveDirectory.isEmpty()) {
            try {
                Path sourcePath = selectedFile.toPath();
                Path destinationPath = Paths.get(saveDirectory, newFileName);

                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Обновляем текстовое поле с новым именем файла
                saveDirectoryTextField.setText(destinationPath.getParent().toString());
            } catch (IOException e) {
                e.printStackTrace();
                displayErrorMessage("Error occurred during file renaming.");
            }
        } else {
            displayErrorMessage("Please select a file, enter a new file name, and choose a save directory.");
        }
    }

    private void deleteFile() {
        if (selectedFile != null && selectedFile.exists()) {
            try {
                Files.delete(selectedFile.toPath());
                saveDirectoryTextField.clear();
                newFileNameTextField.clear();
            } catch (IOException e) {
                e.printStackTrace();
                displayErrorMessage("Error occurred during file deletion.");
            }
        } else {
            displayErrorMessage("Please select a file to delete.");
        }
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
