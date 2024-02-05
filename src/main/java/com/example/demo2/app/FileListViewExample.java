package com.example.demo2.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

/**
 * FileListViewExample
 */
public class FileListViewExample extends Application {

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
        primaryStage.setTitle("File List View Example");

        ListView<String> fileListView = new ListView<>();
        File directory = new File(":\\Java\\telegram\\demo2");
        File[] files = directory.listFiles();
        if (directory.equals(false)) {

            for (File file : files) {
//                if (file.exists()) {
                    fileListView.getItems().add(file.getName());
//                } else {
//                    fileListView.getItems().add("Файлов списке нет");
//                }
            }
        } else {
            fileListView.getItems().add("Файлов списке нет");
        }

        VBox root = new VBox(10);
        root.getChildren().add(fileListView);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
