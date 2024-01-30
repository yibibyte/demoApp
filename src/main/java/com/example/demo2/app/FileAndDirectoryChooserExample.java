package com.example.demo2.app;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class FileAndDirectoryChooserExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Выбор файла
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите файл");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Выбор файла отменен");
            }

            // Выбор директории
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Выберите директорию");
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                System.out.println("Выбранная директория: " + selectedDirectory.getAbsolutePath());
            } else {
                System.out.println("Выбор директории отменен");
            }

            // Пример выбора нескольких файлов
            FileChooser multiFileChooser = new FileChooser();
            multiFileChooser.setTitle("Выберите несколько файлов");
            List<File> selectedFiles = multiFileChooser.showOpenMultipleDialog(primaryStage);

            if (selectedFiles != null) {
                System.out.println("Выбранные файлы:");
                for (File file : selectedFiles) {
                    System.out.println(file.getAbsolutePath());
                }
            } else {
                System.out.println("Выбор файлов отменен");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
