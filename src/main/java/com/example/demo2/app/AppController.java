package com.example.demo2.app;

/**
 * Sample Skeleton for 'app.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jfxtras.scene.control.LocalDateTimePicker;

public class AppController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chooseDirectory"
    private Button chooseDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="chooseFileCopy"
    private Button chooseFileCopy; // Value injected by FXMLLoader

    @FXML // fx:id="defaultDataTime"
    private LocalDateTimePicker defaultDataTime; // Value injected by FXMLLoader

    @FXML // fx:id="defaultPathFolder"
    private CheckBox defaultPathFolder; // Value injected by FXMLLoader

    @FXML // fx:id="deleteFileCopy"
    private Button deleteFileCopy; // Value injected by FXMLLoader

    @FXML // fx:id="nameBackupCopy"
    private TextField nameBackupCopy; // Value injected by FXMLLoader

    @FXML // fx:id="pathFolder"
    private TextField pathFolder; // Value injected by FXMLLoader

    @FXML // fx:id="selectDateTime"
    private LocalDateTimePicker selectDateTime; // Value injected by FXMLLoader

    @FXML // fx:id="startScript"
    private Button startScript; // Value injected by FXMLLoader

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultDataTime.setLocalDateTime(LocalDateTime.now());
        selectDateTime.setLocalDateTime(LocalDateTime.now());
    }

    public void handleStartScript(ActionEvent actionEvent) {

        startScript.setText("Изменили текст кнопки");

//        startScript.setOnAction(actionEvent1 -> {
//            startScript.setText("Change Button Name");
//        });

//        startScript.setOnAction(event -> );

        startScript.setOnAction(event -> {
            String cronDateTime = convertToCronExpression(selectDateTime.getLocalDateTime());
            System.out.println(cronDateTime);


        // Запустить программу в crontab

//                Runtime.getRuntime().exec("echo " + cronDateTime);
            //ProcessBuilder processBuilder = new ProcessBuilder("echo", cronDateTime);
//            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "echo", "This is ProcessBuilder Example from JCG");
            ProcessBuilder pb = new ProcessBuilder("cmd.exe","/C" , "echo", "This is ProcessBuilder Example ");
            try {
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Cron-задача успешно установлена.");
                } else {
                    System.err.println("Ошибка при установке cron-задачи.");
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            //            Process process = null;
//
//            try {
//
//
//                process = Runtime.getRuntime().exec("echo " + cronDateTime);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//
//                process.waitFor();
//
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        });

    }

    public String convertToCronExpression(LocalDateTime dateTime) {
        // Метод преобразует LocalDateTime в cron-выражение
        int minute = dateTime.getMinute();
        int hour = dateTime.getHour();
        int dayOfMonth = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        int dayYear = dateTime.getYear();

        // Предположим, что преобразование упрощенное для демонстрации
        return String.format(Locale.US, "%d %d %d %d %d" ,dayYear, dayOfMonth, month, hour, minute);
    }



    public void handleSelectDateTime(MouseEvent mouseEvent) {

        String time = null;

        if (time == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Вы не выбрали дату или время"); // Предупреждение

        } else {


        }
    }

    public void handleChooseFileCopy(ActionEvent actionEvent) {
    }

    public void handleChooseDirectory(ActionEvent actionEvent) {
    }

    public void handleDeleteFileCopy(ActionEvent actionEvent) {
    }

    public void handleDefaultDataTime(MouseEvent mouseEvent) {
    }

    public void handleDefaultPathFolder(ActionEvent actionEvent) {
    }

    public void handlePathFolder(ActionEvent actionEvent) {
    }

    public void typeNameBackupCopy(ActionEvent actionEvent) {
    }
}
