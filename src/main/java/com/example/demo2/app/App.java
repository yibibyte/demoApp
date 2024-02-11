package com.example.demo2.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Sample Skeleton for 'app.fxml' Controller Class
 * @author G.I.V.
 * @since {@code }
 * @since 1.0
 * @version 1.0
 */
public class App extends Application {

    /**
     * Start метод запуска приложения
     * @author G.I.V.
     * @since {@code }
     * @param stage Параметр метода start для какого окна запускается приложения
     * Результат выполнения метода ничего не возвращает так как void, а значит просто запускает приложения.
     * @since 2.0
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/demo2/app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        stage.setTitle("Backup");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
