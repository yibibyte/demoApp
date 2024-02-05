package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author G.I.V.
 * @version 1.0
 * @since 2024-01-29
 */
public class CronJobController {

    @FXML
    private TextField timeField;

    @FXML
    private TextField dateField;

    /**
     * @throws IOException
     */
    public void onRun() throws IOException {

        Calendar calendar = Calendar.getInstance();
        //  all int
        calendar.set(2023, 1, 28, 12, 0, 0);
        // Получить время и дату запуска из текстовых полей


        //String time = timeField.getText();
        String date = dateField.getText();

        // Преобразовать время и дату в формат cron
        LocalDateTime dateTime = LocalDateTime.parse(/*time + " " +*/ date, DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"));
        String cronExpression = dateTime.format(DateTimeFormatter.ofPattern("0 0 0 ? * *"));

        // Запустить программу в crontab
        Runtime.getRuntime().exec("crontab -e");
        Process process = Runtime.getRuntime().exec("echo " + cronExpression + " >> /etc/crontab");
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
