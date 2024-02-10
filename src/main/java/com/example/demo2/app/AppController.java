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
 * класс AppController Контроллер для связвания UI, то есть нашей заготовки app.fxml с JavaFX элементами(компонентами) в java
 * fx:id в app.fxml должен быть равен названию перемной Элемента (к примеру экземпляру Button)
 * Так же класс Контроллер служит для обработки собитий по нажатию этих элементов
 * @since {@code }
 */
public class AppController implements Initializable {

    private Stage stage;

    /**
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Start Script Copy
     */
    @FXML
    public Button startScriptCopy;

    /**
     * Rename of File
     */
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
        selectDateTime.setLocalDateTime(LocalDateTime.now());
    }


    /**
     * Метод для обработки события кнопки.
     *
     * @param actionEvent
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
                    // ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "tar -czf backup_$(date +%Y%m%d_%H%M%S).tar.gz " + pathFolder.getText());
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

        }

    }

    // День недели нам не нужен, так как нам нужны только год, месяц, число, часы и минуты, которые пользователь будет вводить
    /**
     * Метод convertToCronExpression Метод преобразует LocalDateTime в cron-выражение
     * @param dateTime передаем в наш метод, и это параметр это объект нашего календаря типа LocalDateTimePicker,
     * который отдает нам LocalDateTime dateTime, где мы его уже переводим в int для работы подстановки в cron
     * @return метод возвращает Process для запуска исполнение команд
     */
    //
    public String convertToCronExpression(LocalDateTime dateTime) {

        String minute = String.valueOf(dateTime.getMinute());
        String hour = String.valueOf(dateTime.getHour());
        String day = String.valueOf(dateTime.getDayOfMonth());
        String month = String.valueOf(dateTime.getMonth());
        String year = String.valueOf(dateTime.getYear());

        return  year + "_" + month + "_" + day + "_" + hour + "_" + minute;
    }

    @SuppressWarnings("javadoc")
    public void handleSelectDateTime(MouseEvent mouseEvent) {
    }

    /**
     * @param actionEvent
     */
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

        /**
         * Получаем директорию, к которой принадлежит выбранный файл
         * Объект типо File может быть одновременно как файлом в файловой системе, так и директорией(папкой)
         * Для того чтобы понять с чем мы работаем, есть методы на утверждение parentDirectory.isFile() и parentDirectory.isDirectory()
         * Ранне все было файл один единственный объект parentDirectory в нашем случае является директорией
         * В Java, метод getParentFile() является частью класса File
         * и используется для получения объекта типа File, представляющего родительскую директорию данного файла
         */

        //
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выбор файла отменен");
            alert.setHeaderText("Внимание");
            alert.showAndWait();

            System.out.println("Выбор файла отменен");
        }
    }

    /**
     * Выбор директории для
     *
     * @param actionEvent
     */
    public void handleChooseDirectory(ActionEvent actionEvent) {

        // Выбор файла
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите файл");

        Stage directoryStage = new Stage();
        File chooseFileCopy = directoryChooser.showDialog(directoryStage);

        if (chooseFileCopy != null) {
            pathFolder.setText(chooseFileCopy.getAbsolutePath());

            System.out.println("Выбранный файл: " + chooseFileCopy.getAbsolutePath());
        } else {
            System.out.println("Выбор файла отменен");
        }
    }

    /**
     * Удаление резервной копии
     */
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

    /**
     * @param actionEvent
     */
    public void handleDefaultPathFolder(ActionEvent actionEvent) {

    }

    /**
     * @param actionEvent
     */
    public void handlePathFolder(ActionEvent actionEvent) {
    }

    /**
     * @param event
     */
    @FXML
    void onActionStartListView(ActionEvent event) {
    }

    /**
     * @param actionEvent
     */
    public void typeNameBackupCopy(ActionEvent actionEvent) {
    }

    /**
     * Запуск автоматизированного скрипта с периодичностью по времени
     */
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

                String command = "tar -czf backup_$(date +%Y_%m_%d_%H_%M_%S).tar.gz " + pathFolder.getText();

                /**             Команды для отчета
                # Формирование отчета о создании копии
                echo "Backup created on $(date)" >> backup_report.txt
                du -sh /path/to/backup >> backup_report.txt
                */

                /**     TODO  Сделать CRON
                 *
                 *      !!!!! CRON !!!!
                 *      Добавление задачи по крону и там же должна быть задача по выводу отчет когда будет делаться резервное копирование
                 *
                 */


                // + Отчет или же альтернатива готовый bash скрипт
                // TODO Добавить в command еще команду для формирования отчета:  + echo "Backup created on $(date)" >> backup_report.txt
                // смотри файл backup.sh в проекте
                //   ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
                // ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "tar -czf backup_$(date +%Y%m%d_%H%M%S).tar.gz " + pathFolder.getText());

/*

                // Строка с командами для выполнения в bash
                String bashCommands = "tar -czf backup_$(date +%Y_%m_%d_%H_%M_%S).tar.gz /path/to/directory && " +
                        "echo \"Backup created on $(date)\" >> backup_report.txt && " +
                        "du -sh /path/to/backup >> backup_report.txt";

                // Создание объекта ProcessBuilder с использованием списка команд
                ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", bashCommands);

*/


                /**
                 *  Нужно загнать в массив все строки, чтобы получился массив строк с помощью Arrays.asList() передать как параметр в new ProcessBuilder()
                 *  для исполнения
                 */

                /*

                // Команда для создания архива с резервной копией
                String tarCommand = "tar -czf backup_$(date +%Y_%m_%d_%H_%M_%S).tar.gz /path/to/directory";

                // Команда для добавления информации в файл отчета о резервном копировании
                String echoCommand = "echo \"Backup created on $(date)\" >> backup_report.txt";

                // Команда для добавления размера резервной копии в файл отчета
                String duCommand = "du -sh /path/to/backup >> backup_report.txt";

                // Создание объекта ProcessBuilder с использованием списка команд
                ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList("bash", "-c", tarCommand + " && " + echoCommand + " && " + duCommand));

                // Запуск процесса
                Process process = processBuilder.start();

                // Ожидание завершения процесса
                int exitCode = process.waitFor();

                 */


                //String command = "bash backup.sh";

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


//            ProcessBuilder для Windows
//            ProcessBuilder pbCMD = new ProcessBuilder("cmd.exe", "/C", "echo", cronDateTime);
//            try {

        }
    }

    /**
     * Переименование файла
     */
    public void handleRenameFile(ActionEvent actionEvent) {

        File oldFile = new File(nameBackupCopy.getText());
        if (nameBackupCopy.getText() != null) {
            String newName = nameBackupCopy.getText();
            File newFile = new File(oldFile.getParent(), newName);

            if (oldFile.renameTo(newFile)) {
                System.out.println("Файл успешно переименован в: " + newFile.getAbsolutePath());
            } else {
                System.out.println("Не удалось переименовать файл.");
            }
        }

        // Создание текстового поля для ввода нового имени файла

        TextField newFileNameTextField = new TextField();
        // Создание кнопки для запуска процесса переименования файла
        Button renameButton = new Button("Переименовать файл");

        // Логика для переименования файла при нажатии кнопки
        renameButton.setOnAction(event -> {
            String newFileName = newFileNameTextField.getText(); // Получаем новое имя файла из текстового поля

            File newFile = new File(oldFile.getParent(), newFileName); // Создаем новый объект File с новым именем в той же директории

            if (oldFile.renameTo(newFile)) { // Пытаемся переименовать файл
                System.out.println("Файл успешно переименован.");
            } else {
                System.out.println("Не удалось переименовать файл.");
            }
        });

/*
        if (newFileNameTextField != null) {

            // Показываем путь к файлу в Текстовом поле TextField
            nameBackupCopy.setText(newFile.getAbsolutePath());

            // Получаем директорию, к которой принадлежит выбранный файл
            File parentDirectory = newFile.getParentFile();

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


            System.out.println("Выбранный файл: " + newFile.getAbsolutePath());
        } else {
            System.out.println("Нет файла для переименования");
        }*/


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

    /**
     * Запуск резервного копирования в текущий момент
     */
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
