package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 *
 */
public class HelloApplication extends Application {
    /**
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

/*
        1. Выбор директории для сохранения.
        2. Выбор директории для хранилища по умолчанию
        3. Редактирование шаблона имени по умолчанию резервной копии ( имя должно содержать дату и о времени создания резервной копии)
        4.Запуск создания резервной копии или настройка даты и времени для автоматического создания копий;
        5.Настройка автоматического резервного копирования: настройка места хранения и периодичности создания резервных копий.
*/
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

/*      Установка языка
        Locale ru = new Locale("ru", "RU");
        Locale.setDefault(ru);
        getLocaleInfo(ru);
        */

        launch();
    }
/*    private static void getLocaleInfo(Locale current) {
        System.out.println("Код региона: " + current.getCountry());
        System.out.println("Название региона: " + current.getDisplayCountry());
        System.out.println("Код языка региона: " + current.getLanguage());
        System.out.println("Название языка региона: "
                + current.getDisplayLanguage());
        System.out.println();
    }*/

}