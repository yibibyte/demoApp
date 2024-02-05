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

/**
 * FileManipulationApp
 */
public class FileManipulationApp extends Application {

    private File selectedFile;
    private TextField saveDirectoryTextField;
    private TextField defaultSaveDirectoryTextField;
    private TextField newFileNameTextField;

    /**
     * @param args
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("App");

        saveDirectoryTextField = new TextField();
        saveDirectoryTextField.setPromptText("Выбериет директорию для сохранения файла...");
        saveDirectoryTextField.setEditable(false);

        Button chooseSaveDirectoryButton = new Button("Выбериет директорию");
        chooseSaveDirectoryButton.setOnAction(e -> chooseSaveDirectory());

        defaultSaveDirectoryTextField = new TextField();
        defaultSaveDirectoryTextField.setPromptText("Уставноить директорию по умолчанию...");

        newFileNameTextField = new TextField();
        newFileNameTextField.setPromptText("Введите имя нового файла...");

        Button renameButton = new Button("Переименовать файл");
        renameButton.setOnAction(e -> renameFile());

        Button deleteButton = new Button("Удалить файл");
        deleteButton.setOnAction(e -> deleteFile());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Выбериет директорию для сохранения файла:"),
                saveDirectoryTextField,
                chooseSaveDirectoryButton,
                new Label("Уставноить директорию по умолчанию:"),
                defaultSaveDirectoryTextField,
                new Label("Введите имя нового файла:"),
                newFileNameTextField,
                renameButton,
                deleteButton
        );

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     */
    private void chooseSaveDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите директорию");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            saveDirectoryTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     * @param message
     */
    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
