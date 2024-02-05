package com.example.demo2.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTimePicker;

/**
 * @author G.I.V.
 * @description класс AppController Контроллер для связвания UI, то есть нашей заготовки app.fxml с JavaFX элементами(компонентами) в java
 * fx:id в app.fxml должен быть равен названию перемной Элемента (к примеру экземпляру Button)
 * Так же класс Контроллер служит для обработки собитий по нажатию этих элементов
 * @since {@code }
 */
public class AppController implements Initializable {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public Button startScriptCopy;

    @FXML
    private Button renameFile;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chooseDirectory"
    private Button chooseDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="chooseFileCopy"
    private Button chooseFileCopy; // Value injected by FXMLLoader

    @FXML // fx:id="defaultPathFolder"
    private CheckBox defaultPathFolder; // Value injected by FXMLLoader

    @FXML // fx:id="deleteFileCopy"
    private Button deleteFileCopy; // Value injected by FXMLLoader

    @FXML
    private Label labelPath;

    @FXML
    private Label labelPath1;

    @FXML
    private Button startAutoScript;

    @FXML
    private ListView<String> listView;

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
        //defaultDataTime.setLocalDateTime(LocalDateTime.now());
        selectDateTime.setLocalDateTime(LocalDateTime.now());
    }

    /**
     * Метод для обработки события кнопки.
     *
     * @param actionEvent Событие кнопки.
     */
    public void handleStartScript(ActionEvent actionEvent) {

        if (selectDateTime.getLocalDateTime() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали дату или время");
            alert.setHeaderText("Внимание");
            alert.showAndWait();
        } else {
            String cronDateTime = convertToCronExpression(selectDateTime.getLocalDateTime());
            System.out.println(cronDateTime);

            /*  Построение команды для установки crontab
            Команды для управления crontab:
            crontab -e: Редактирование crontab файла.
            crontab -l: Просмотр текущих задач.
            crontab -r: Удаление текущих задач.

             * Метод start для обработки события кнопки.
             * @param у конструктора есть параметры, которые запускает cmd.exe с ключом /C и выводит сообщение на экран
             * @return конструктор возвращает ProcessBuilder для дальнейшего запуска метода start для исполнение команд

             String[] arrayCommandCron = {"crontab", "-e", "-l", "|", "echo", "\"" + cronExpression + "\"", "|", "crontab", "-"};

            String commandCron = "echo \"0 3 * * * /путь/к/вашей/программе\" | crontab -\n";

            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "echo", "команда echo, котрая выводит это сообщение на экран ");
            */

            if (pathFolder.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали путь к директории");
                alert.setHeaderText("Внимание");
                alert.showAndWait();
            } else {

                String command = "tar -czf backup_$(date +%Y%m%d_%H%M%S).tar.gz " + pathFolder.getText();

                // + Отчет или же альтернатива готовый bash скрипт
                // TODO Добавить в command еще команду для формирования отчета:  + echo "Backup created on $(date)" >> backup_report.txt
                // смотри файл backup.sh в проекте

                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
                    Process process = processBuilder.start();

                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Команда резервного копирования выполнена успешно");
                        alert.setHeaderText("Внимание");
                        alert.showAndWait();
                        System.out.println("Команда резервного копирования выполнена успешно.");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выполнения команды резервного копирования");
                        alert.setHeaderText("Ошибка");
                        alert.showAndWait();
                        System.out.println("Ошибка выполнения команды резервного копирования.");
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


//            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "echo", cronDateTime);
//            try {
//
//                /**
//                 * Метод start для запуска команды
//                 * @param у метода нет параметров
//                 * @return метод возвращает Process для запуска исполнение команд
//                 */
//                Process process = pb.start();
//                int exitCode = process.waitFor();
//
//                if (exitCode == 0) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Команда резервного копирования выполнена успешно");
//                    alert.setHeaderText("Внимание");
//                    alert.showAndWait();
//                    System.out.println("Команда резервного копирования выполнена успешно.");
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выполнения команды резервного копирования");
//                    alert.setHeaderText("Ошибка");
//                    alert.showAndWait();
//                    System.out.println("Ошибка выполнения команды резервного копирования.");
//                }
//
//            } catch (IOException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }


        }

    }

    /**
     * Метод convertToCronExpression Метод преобразует LocalDateTime в cron-выражение
     *
     * @param dateTime передаем в наш метод, и это параметр это объект нашего календаря типа LocalDateTimePicker,
     *                 который отдает нам LocalDateTime dateTime, где мы его уже переводим в int для работы подстановки в cron
     * @return метод возвращает Process для запуска исполнение команд
     */
    //
    public String convertToCronExpression(LocalDateTime dateTime) {

        // День недели на не нужен так как у нам нужны только год, месяц, число, часы и минуты, которые пользоватлеь будет вводить пользователь
        /**
         * Метод convertToCronExpression Метод преобразует LocalDateTime в cron-выражение
         * @param dateTime передаем в наш метод, и это параметр это объект нашего календаря типа LocalDateTimePicker,
         * который отдает нам LocalDateTime dateTime, где мы его уже переводим в int для работы подстановки в cron
         * @return метод возвращает Process для запуска исполнение команд
         */
        int minute = dateTime.getMinute();

//        String min = String.valueOf(minute);
//        String min = String.valueOf(dateTime.getMinute());

        int hour = dateTime.getHour();
        int dayOfMonth = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        //int dayOfWeek = dateTime.getDayOfWeek().getValue();
        int dayYear = dateTime.getYear();

        // Предположим, что преобразование упрощенное для демонстрации
        return String.format(Locale.US, "%d %d %d %d %d", dayYear, dayOfMonth, month, hour, minute);
    }


    public void handleSelectDateTime(MouseEvent mouseEvent) {
    }

    public void handleChooseFileCopy(ActionEvent actionEvent) {
        // Выбор файла

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        // Показываем окно выбора файла
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Добавление фильтра в окно FileChoose
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            // Показываем путь к файлу в Текстовом поле TextField
            nameBackupCopy.setText(selectedFile.getAbsolutePath());

            // Получаем директорию, к которой принадлежит выбранный файл
            File parentDirectory = selectedFile.getParentFile();

            // Получаем список файлов в этой директории
            File[] filesInDirectory = parentDirectory.listFiles();

            if (filesInDirectory != null) {
                // Получаем имена файлов
                List<String> fileNames = new ArrayList<>();
                for (File file : filesInDirectory) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }

                // Обновляем ListView
                ObservableList<String> fileList = FXCollections.observableArrayList(fileNames);
                listView.setItems(fileList);
            }


            System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Выбор файла отменен");
        }
    }

    public void handleChooseDirectory(ActionEvent actionEvent) {

        // Выбор файла

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите файл");

        // Показываем окно выбора файла

        Stage directoryStage = new Stage();
        File chooseFileCopy = directoryChooser.showDialog(directoryStage);

        if (chooseFileCopy != null) {
            pathFolder.setText(chooseFileCopy.getAbsolutePath());

            System.out.println("Выбранный файл: " + chooseFileCopy.getAbsolutePath());
        } else {
            System.out.println("Выбор файла отменен");
        }
    }

    public void handleDeleteFileCopy(ActionEvent actionEvent) {
        String filePath = nameBackupCopy.getText();

        if (!filePath.isEmpty()) {
            File fileToDelete = new File(filePath);

            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    System.out.println("Файл удален: " + filePath);
                    nameBackupCopy.clear();
                } else {
                    System.out.println("Не удалось удалить файл: " + filePath);
                }
            } else {
                System.out.println("Файл не существует: " + filePath);
            }
        } else {
            System.out.println("Введите путь к файлу.");
        }

    }

    public void handleDefaultPathFolder(ActionEvent actionEvent) {

    }

    public void handlePathFolder(ActionEvent actionEvent) {
    }

    @FXML
    void onActionStartListView(ActionEvent event) {

    }

    public void typeNameBackupCopy(ActionEvent actionEvent) {
    }

    public void onActionStartAutoScript(ActionEvent actionEvent) {

        if (selectDateTime.getLocalDateTime() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали дату или время");
            alert.setHeaderText("Внимание");
            alert.showAndWait();
        } else {
            String cronDateTime = convertToCronExpression(selectDateTime.getLocalDateTime());
            System.out.println(cronDateTime);

            /**  Построение команды для установки crontab
             Команды для управления crontab:
             crontab -e: Редактирование crontab файла.
             crontab -l: Просмотр текущих задач.
             crontab -r: Удаление текущих задач.
             * Метод start для обработки события кнопки.
             * @param у конструктора есть параметры, которые запускает cmd.exe с ключом /C и выводит сообщение на экран
             * @return конструктор возвращает ProcessBuilder для дальнейшего запуска метода start для исполнение команд

            String[] arrayCommandCron = {"crontab", "-e", "-l", "|", "echo", "\"" + cronExpression + "\"", "|", "crontab", "-"};

            String commandCron = "echo \"0 3 * * * /путь/к/вашей/программе\" | crontab -\n";

            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "echo", "команда echo, котрая выводит это сообщение на экран ");
             */

            if (pathFolder.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали путь к директории");
                alert.setHeaderText("Внимание");
                alert.showAndWait();
            } else {

                String command = "bash backup.sh";

                try {
                    ProcessBuilder processBuilderBASH = new ProcessBuilder("bash", "-c", command);
                    Process process = processBuilderBASH.start();

                    int exitCode = process.waitFor();

                    if (exitCode == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Команда резервного копирования выполнена успешно");
                        alert.setHeaderText("Внимание");
                        alert.showAndWait();
                        System.out.println("Команда резервного копирования выполнена успешно.");

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выполнения команды резервного копирования");
                        alert.setHeaderText("Ошибка");
                        alert.showAndWait();
                        System.out.println("Ошибка выполнения команды резервного копирования.");
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


//            ProcessBuilder pbCMD = new ProcessBuilder("cmd.exe", "/C", "echo", cronDateTime);
//            try {
//
//                /**
//                 * Метод start для запуска команды
//                 * @param у метода нет параметров
//                 * @return метод возвращает Process для запуска исполнение команд
//                 */
//                Process process = pbCMD.start();
//                int exitCode = process.waitFor();
//
//                if (exitCode == 0) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Команда резервного копирования выполнена успешно");
//                    alert.setHeaderText("Внимание");
//                    alert.showAndWait();
//                    System.out.println("Команда резервного копирования выполнена успешно.");
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выполнения команды резервного копирования");
//                    alert.setHeaderText("Ошибка");
//                    alert.showAndWait();
//                    System.out.println("Ошибка выполнения команды резервного копирования.");
//                }
//
//            } catch (IOException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }


        }
    }

    public void handleRenameFile(ActionEvent actionEvent) {

        File selectedFile = new File(nameBackupCopy.getText());
        if (nameBackupCopy.getText() != null) {
            String newName = nameBackupCopy.getText();
            File newFile = new File(selectedFile.getParent(), newName);

            if (selectedFile.renameTo(newFile)) {
                System.out.println("Файл успешно переименован в: " + newFile.getAbsolutePath());
            } else {
                System.out.println("Не удалось переименовать файл.");
            }
        }

        if (selectedFile != null) {

            // Показываем путь к файлу в Текстовом поле TextField
            nameBackupCopy.setText(selectedFile.getAbsolutePath());

            // Получаем директорию, к которой принадлежит выбранный файл
            File parentDirectory = selectedFile.getParentFile();

            // Получаем список файлов в этой директории
            File[] filesInDirectory = parentDirectory.listFiles();

            if (filesInDirectory != null) {
                // Получаем имена файлов
                List<String> fileNames = new ArrayList<>();
                for (File file : filesInDirectory) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }

                // Обновляем ListView
                ObservableList<String> fileList = FXCollections.observableArrayList(fileNames);
                listView.setItems(fileList);
            }


            System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Нет файла для переименования");
        }


//        String currentFilePath = nameBackupCopy.getText();
//
//        if (!currentFilePath.isEmpty()) {
//            File currentFile = new File(currentFilePath);
//
//            if (currentFile.exists()) {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Переименовать файл");
//                fileChooser.setInitialFileName(currentFile.getName());
//
//                File newFile = fileChooser.showSaveDialog(null);
//
//                if (newFile != null) {
//                    if (currentFile.renameTo(newFile)) {
//                        nameBackupCopy.setText(newFile.getAbsolutePath());
//                    } else {
//                        System.out.println("Не удалось переименовать файл.");
//                    }
//                }
//            } else {
//                System.out.println("Файл не существует: " + currentFilePath);
//            }
//        } else {
//            System.out.println("Введите путь к файлу.");
//        }
    }

    public void handleStartScriptCopy(ActionEvent actionEvent) {
        if (selectDateTime.getLocalDateTime() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали дату или время");
            alert.setHeaderText("Внимание");
            alert.showAndWait();
        } else {
            String cronDateTime = convertToCronExpression(selectDateTime.getLocalDateTime());
            System.out.println(cronDateTime);

            /**  Построение команды для установки crontab
             Команды для управления crontab:
             crontab -e: Редактирование crontab файла.
             crontab -l: Просмотр текущих задач.
             crontab -r: Удаление текущих задач.
             * Метод start для обработки события кнопки.
             * @param у конструктора есть параметры, которые запускает cmd.exe с ключом /C и выводит сообщение на экран
             * @return конструктор возвращает ProcessBuilder для дальнейшего запуска метода start для исполнение команд

            String[] arrayCommandCron = {"crontab", "-e", "-l", "|", "echo", "\"" + cronExpression + "\"", "|", "crontab", "-"};

            String commandCron = "echo \"0 3 * * * /путь/к/вашей/программе\" | crontab -\n";

            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "echo", "команда echo, котрая выводит это сообщение на экран ");
             */

            if (pathFolder.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали путь к директории");
                alert.setHeaderText("Внимание");
                alert.showAndWait();
            } else {

                // TODO Скрипт который можно взять для запуска ранее созданными резервное копирование
                String command = "bash backup.sh";

                try {
                    ProcessBuilder processBuilderBASH = new ProcessBuilder("bash", "-c", command);
                    Process process = processBuilderBASH.start();

                    int exitCode = process.waitFor();

                    if (exitCode == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Команда резервного копирования выполнена успешно");
                        alert.setHeaderText("Внимание");
                        alert.showAndWait();
                        System.out.println("Команда резервного копирования выполнена успешно.");

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ошибка выполнения команды резервного копирования");
                        alert.setHeaderText("Ошибка");
                        alert.showAndWait();
                        System.out.println("Ошибка выполнения команды резервного копирования.");
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
