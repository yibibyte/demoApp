package com.example.demo2.app;

import com.example.demo2.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @description Sample Skeleton for 'app.fxml' Controller Class
 * @author G.I.V.
 * @since {@code }
 * @return Результат выполнения метода.
 * @since 1.0
 * @version 1.0
 */
public class App extends Application {

    /**
     * @description start метод запуска приложения
     * @author G.I.V.
     * @since {@code }
     * @param stage Параметр метода start для какого окна запускается приложения
     * @return Результат выполнения метода ничего не возвращает так как void, а значит просто запускает приложения.
     * @since 2.0
     */
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
    /**
     * @author G.I.V.
     * @since {@code }
     *
     * @description Метод public static void main(String[] args) является точкой входа в программу в языке Java.
     * Он используется для запуска Java приложения, именно этот метод вызывается при запуске программы.
     * В метод main передаются аргументы командной строки в виде массива строк (String[] args),
     * который содержит параметры, переданные программе при ее запуске.
     *
     * public: Метод является открытым и доступным из любого места в программе.
     * static: Метод принадлежит классу, а не конкретному объекту класса. Это означает, что метод main может быть вызван без создания экземпляра класса.
     * void: Метод не возвращает никакого значения.
     * main: Имя метода.
     * String[] args: Параметры, переданные программе при ее запуске, представленные в виде массива строк.
     *
     * Для вызова метода main необходимо запустить Java-приложение, например, с помощью команды java в командной строке.
     * При запуске программы аргументы командной строки можно передать после имени класса в виде отдельных строк, разделенных пробелами.
     *
     * Пример запуска в терминале или командной строке (bash или cmd), где мы модем запускать наше приложения с разными параметрами:
     * java App arg1 arg2 arg3
     *
     * @param args массив аргументов, которые подаются на вход методу main, если нужно запускать нашу программу дополнительными параметрами
     * @return Результат выполнения метода ничего не возвращает так как void, а значит просто запускает приложения.
     */
    public static void main(String[] args) {
        launch();
    }
}
