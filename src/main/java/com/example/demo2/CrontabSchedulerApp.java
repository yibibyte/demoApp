package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import org.controlsfx.control.*;


/**
 * CrontabSchedulerApp
 */
public class CrontabSchedulerApp /*extends Application*/ {
//
//        public static void main(String[] args) {
//            launch(args);
//        }
//
//        @Override
//        public void start(Stage primaryStage) {
//            primaryStage.setTitle("Crontab Scheduler");
//
//            // Создаем DateTimePicker
//            DateTimePicker dateTimePicker = new DateTimePicker();
//
//            // Метка для отображения выбранного времени
//            Label selectedTimeLabel = new Label("Selected Time: ");
//
//            // Слушатель изменений для обновления метки
//            dateTimePicker.dateTimeProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue != null) {
//                    selectedTimeLabel.setText("Selected Time: " + newValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//                }
//            });
//
//            // Кнопка для выполнения crontab
//            javafx.scene.control.Button executeButton = new javafx.scene.control.Button("Execute Crontab");
//            executeButton.setOnAction(event -> {
//                try {
//                    executeCrontabCommand(dateTimePicker.getDateTimeValue());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//
//            // Располагаем элементы в VBox
//            VBox vbox = new VBox(20);
//            vbox.getChildren().addAll(dateTimePicker, selectedTimeLabel, executeButton);
//
//            // Создаем сцену
//            Scene scene = new Scene(vbox, 300, 200);
//
//            // Устанавливаем сцену и отображаем окно
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }
//
//        // Выполнение команды crontab с выбранным временем
//        private void executeCrontabCommand(LocalDateTime selectedDateTime) throws IOException {
//            String crontabExpression = convertToCrontabExpression(selectedDateTime);
//
//            // Вместо вывода команды echo, выполните свою фактическую команду
//            ProcessBuilder processBuilder = new ProcessBuilder("echo", "Selected time in crontab format: " + crontabExpression);
//            Process process = processBuilder.start();
//
//            // Получаем код завершения
//            int exitCode;
//            try {
//                exitCode = process.waitFor();
//            } catch (InterruptedException e) {
//                throw new IOException("Error waiting for process completion.", e);
//            }
//
//            // Печатаем результат выполнения команды
//            System.out.println("Process exited with code: " + exitCode);
//        }
//
//        // Преобразование LocalDateTime в выражение crontab
//        private String convertToCrontabExpression(LocalDateTime dateTime) {
//            return String.format("%d %d %d %d %d",
//                    dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear());
//        }
//    }

}
