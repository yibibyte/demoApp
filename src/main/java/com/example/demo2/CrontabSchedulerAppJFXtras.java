package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTimePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrontabSchedulerAppJFXtras extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Crontab Scheduler (JFXtras)");

        // Создаем LocalDateTimePicker
        LocalDateTimePicker localDateTimePicker = new LocalDateTimePicker();

        // Метка для отображения выбранного времени
        Button executeButton = new Button("Execute Crontab");
        executeButton.setOnAction(event -> {
            LocalDateTime selectedDateTime = localDateTimePicker.getLocalDateTime();
            executeCrontabCommand(selectedDateTime);
        });

        // Располагаем элементы в VBox
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(localDateTimePicker, executeButton);

        // Создаем сцену
        Scene scene = new Scene(vbox, 500, 300);

        // Устанавливаем сцену и отображаем окно
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Выполнение команды crontab с выбранным временем
    private void executeCrontabCommand(LocalDateTime selectedDateTime) {
        String crontabExpression = convertToCrontabExpression(selectedDateTime);

        // Здесь вы можете использовать ProcessBuilder для выполнения фактической команды в Linux
        System.out.println("Selected time in crontab format: " + crontabExpression);
    }

    // Преобразование LocalDateTime в выражение crontab
    private String convertToCrontabExpression(LocalDateTime dateTime) {
        return String.format("%d %d %d %d %d",
                dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear());
    }
}
