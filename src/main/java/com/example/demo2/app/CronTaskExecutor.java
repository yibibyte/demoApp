package com.example.demo2.app;

import jfxtras.scene.control.LocalDateTimePicker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CronTaskExecutor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cron Task Executor");

        LocalDateTimePicker dateTimePicker = new LocalDateTimePicker();
        Button executeButton = new Button("Запустить cron-задачу");

        executeButton.setOnAction(event -> {
//            String cronExpression = convertToCronExpression(dateTimePicker.getDateTimePermissive());
            String cronExpression = convertToCronExpression(dateTimePicker.getLocalDateTime());
            System.out.println("Генерируемая cron-задача: " + cronExpression);

            try {
                // Записываем cron-задачу в файл
                Path cronFile = Files.createTempFile("cron_task_", ".txt");
                Files.write(cronFile, List.of(cronExpression), StandardOpenOption.CREATE);

                // Устанавливаем cron-задачу с помощью crontab
                ProcessBuilder processBuilder = new ProcessBuilder("crontab", cronFile.toString());
                Process process = processBuilder.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Cron-задача успешно установлена.");
                } else {
                    System.err.println("Ошибка при установке cron-задачи.");
                }

                // Удаляем временный файл
                Files.deleteIfExists(cronFile);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        VBox vbox = new VBox(dateTimePicker, executeButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Преобразование LocalDateTime в строку с cron-выражением
    private String convertToCronExpression(java.time.LocalDateTime dateTime) {
        int minute = dateTime.getMinute();
        int hour = dateTime.getHour();
        int dayOfMonth = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int dayOfWeek = dateTime.getDayOfWeek().getValue();

        // Преобразование в cron-выражение
        return String.format("%d %d %d %d %d", minute, hour, dayOfMonth, month, dayOfWeek);
    }
}
