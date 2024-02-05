package com.example.demo2.app;

import javafx.scene.control.Alert;
import jfxtras.scene.control.LocalDateTimePicker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 */
public class CronTask extends Application {

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
        primaryStage.setTitle("Cron Задача");

        LocalDateTimePicker dateTimePicker = new LocalDateTimePicker();
        Button executeButton = new Button("Запустить cron-задачу");

        executeButton.setOnAction(event -> {
            if (dateTimePicker.getLocalDateTime() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setHeaderText("Внимание");
                alert.setContentText("Введите дату и время");
                alert.show();
            } else {
            LocalDateTime dateTime = dateTimePicker.getLocalDateTime();
            String cronExpression = convertToCronExpression(dateTime);
            System.out.println("Генерируемая cron-задача: " + cronExpression);
            }
            // Вместо вывода на консоль вы можете выполнить установку cron-задачи
        });

        VBox vbox = new VBox(dateTimePicker, executeButton);
        Scene scene = new Scene(vbox, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Преобразование LocalDateTime в строку с cron-выражением
     */
    private String convertToCronExpression(LocalDateTime dateTime) {
        // Метод преобразует LocalDateTime в cron-выражение
        int minute = dateTime.getMinute();
        int hour = dateTime.getHour();
        int dayOfMonth = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int dayOfWeek = dateTime.getDayOfWeek().getValue();

        // Предположим, что преобразование упрощенное для демонстрации
        return String.format(Locale.US, "%d %d %d %d %d", minute, hour, dayOfMonth, month, dayOfWeek);
    }
}
