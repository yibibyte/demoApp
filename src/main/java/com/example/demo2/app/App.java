package com.example.demo2.app;

import com.example.demo2.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        stage.setTitle("Backup");
        stage.setScene(scene);
        stage.show();

//        1. Выбор директории для сохранения.
//        2. Выбор директории для хранилища по умолчанию
//        3. Редактирование шаблона имени по умолчанию резервной копии ( имя должно содержать дату и о времени создания резервной копии)
//        4.Запуск создания резервной копии или настройка даты и времени для автоматического создания копий;
//        5.Настройка автоматического резервного копирования: настройка места хранения и периодичности создания резервных копий.
    }

    public static void main(String[] args) {
        launch();
    }
}
