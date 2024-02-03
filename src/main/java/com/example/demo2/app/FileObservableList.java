package com.example.demo2.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;

public class FileObservableList extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ObservableList<String> langs = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
        ListView<String> langsListView = new ListView<String>(langs);
        langsListView.setPrefSize(250, 150);

        Button btn = new Button("Change");

        btn.setOnAction(event -> {

            ObservableList<String> newLangs = FXCollections.observableArrayList("PHP", "Go", "C++");
            langsListView.setItems(newLangs);
        });

        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, btn, langsListView);
        Scene scene = new Scene(root, 300, 250);

        stage.setScene(scene);
        stage.setTitle("ListView in JavaFX");
        stage.show();
    }
}