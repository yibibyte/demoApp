package com.example.demo2.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 *
 * Создание кнопки для выбора директории сохранения резервной копии м
 * с помощью DirectoryChooser
 * @author G.I.V.
 * @since {@code }
 */
public class BackupApplication extends Application {

    private String backupDirectory;
    private String storageDirectory;
    private String defaultBackupNameTemplate;
    private String backupDateTime;
    private String backupSize;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Backup Application");

        // Создание кнопки для выбора директории сохранения резервной копии
        Button backupDirectoryButton = new Button("Выбрать директорию для сохранения резервной копии");
        backupDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                backupDirectory = selectedDirectory.getAbsolutePath();
            }
        });

        // Создание кнопки для выбора директории хранилища резервных копий по умолчанию
        Button storageDirectoryButton = new Button("Выбрать директорию для хранилища резервных копий по умолчанию");
        storageDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                storageDirectory = selectedDirectory.getAbsolutePath();
            }
        });

        // Создание кнопки для редактирования шаблона имени по умолчанию резервной копии
        Button editNameTemplateButton = new Button("Редактировать шаблон имени по умолчанию резервной копии");
        editNameTemplateButton.setOnAction(e -> {
            // Здесь можно добавить логику для редактирования шаблона имени по умолчанию
        });

        // Создание кнопки для запуска создания резервной копии или настройки автоматического создания копий
        Button createBackupButton = new Button("Создать резервную копию");
        createBackupButton.setOnAction(e -> {
            // Здесь можно добавить логику для создания резервной копии
        });

        // Создание кнопки для настройки автоматического резервного копирования
        Button configureAutoBackupButton = new Button("Настроить автоматическое резервное копирование");
        configureAutoBackupButton.setOnAction(e -> {
            // Здесь можно добавить логику для настройки автоматического резервного копирования
        });

        VBox vbox = new VBox(backupDirectoryButton, storageDirectoryButton, editNameTemplateButton, createBackupButton, configureAutoBackupButton);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
