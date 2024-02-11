package com.example.demo2.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private boolean buttonClicked = false;

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

    @SuppressWarnings("javadoc")
    public void handleSelectDateTime(MouseEvent mouseEvent) {
    }

    /**
     * Метод для обработки события кнопки.
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

            if (pathFolder.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали путь к директории");
                alert.setHeaderText("Внимание");
                alert.showAndWait();
            } else {

                //String command = "tar -czf backup_$(date +%Y%m%d_%H%M%S).tar.gz " + pathFolder.getText();
                //String command = "sudo tar -zcvf /etc-`date '+%F'`.tar.gz " + pathFolder.getText();
                //String command = "sudo tar -zcvf /etc-$(date +%F).tar.gz " + pathFolder.getText();

                //String commandAll = "tar -zcvf " + pathFolder.getText() + "/backup-`date '+%F'`.tar.gz /etc; echo \"Резервная копия создана $(date)\" >> \"backup report $(date)\".txt && du -sh " + pathFolder.getText() + " >>\"backup report $(date)\".txt";

                String commandTar = "tar -zcvf " + pathFolder.getText() + "/backup-`date '+%F'`.tar.gz /etc";
                String commanEcho = "echo \"Резервная копия создана $(date)\" >> " + pathFolder.getText() + "/\"backup report $(date)\".txt";
                String commanDu = "du -sh " + pathFolder.getText() + "/ >> \"backup report $(date)\".txt";

                String command2 = "echo \"Резервная копия создана $(date)\" >> " + pathFolder.getText() + "/\"backup report $(date)\".txt && du -sh " + pathFolder.getText() + "/ >> \"backup report $(date)\".txt";

                // TODO Добавить в command  для формирования отчета или же альтернатива готовый bash скрипт:  + echo "Backup created on $(date)" >> backup_report.txt

//                # Формирование отчета о создании копии
//                echo "Backup created on $(date)" >> backup_report.txt
//                du -sh /path/to/backup_directory >> backup_report.txt


                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", commandTar, command2);
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
        String dayOfMonth = String.valueOf(dateTime.getDayOfMonth());
        String month = String.valueOf(dateTime.getMonthValue());
        String dayOfWeek = String.valueOf(dateTime.getDayOfWeek().getValue());
        //String year = String.valueOf(dateTime.getYear());

        return  minute + " " + hour + " " + dayOfMonth + " " + month + " " + dayOfWeek;
    }

    /**
     * @param actionEvent
     */
    public void handleChooseFileCopy(ActionEvent actionEvent) {

        // Выбор файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        // Добавление фильтра в окно FileChoose
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("tar files (*.tar.gz)", "*.tar.gz");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем окно выбора файла
        File selectedFile = fileChooser.showOpenDialog(stage);

        //File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            // Показываем путь к файлу в Текстовом поле TextField
            nameBackupCopy.setText(selectedFile.getAbsolutePath());

            File parentDirectory = selectedFile.getParentFile();

            // Получаем список файлов в этой директории
            File[] filesInDirectory = parentDirectory.listFiles();

            if (filesInDirectory != null) {
                // Получаем имена файлов
                List<String> fileNames = new ArrayList<>();
                for (File file : filesInDirectory) {
                    if (file.isFile() && file.getName().endsWith(".tar.gz")) {
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


        /*


        // Создается объект FileChooser с именем fileChooser. FileChooser - это класс в JavaFX, который позволяет пользователю выбрать файл или директорию на компьютере.
        FileChooser fileChooser = new FileChooser();

        // Метод setTitle() устанавливает заголовок диалогового окна выбора файла. В данном случае, заголовок установлен как "Выберите файл".
        fileChooser.setTitle("Выберите файл");

        // Метод showOpenDialog() отображает диалоговое окно выбора файла. Если пользователь выбирает файл, метод возвращает объект File, представляющий выбранный файл. Если пользователь отменяет выбор файла, метод возвращает null.
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Создается объект ExtensionFilter с именем extFilter. ExtensionFilter - это класс в JavaFX, который позволяет ограничить выбор файлов определенным расширением. В данном случае, фильтр ограничивает выбор файлов с расширением .tar.gz.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("tar files (*.tar.gz)", "*.tar.gz");

        // Метод getExtensionFilters() возвращает список фильтров, которые применяются к диалоговому окну выбора файла. Метод add() добавляет новый фильтр в этот список.
        fileChooser.getExtensionFilters().add(extFilter);

        // Если выбранный файл не равен null, то выполняется блок кода. Это означает, что пользователь выбрал файл.
        if (selectedFile != null) {

        // Метод getAbsolutePath() возвращает абсолютный путь к выбранному файлу. Этот путь затем устанавливается в текстовое поле nameBackupCopy.
        nameBackupCopy.setText(selectedFile.getAbsolutePath());

        // Метод getParentFile() возвращает родительскую директорию выбранного файла.
        File parentDirectory = selectedFile.getParentFile();

        // Метод listFiles() возвращает массив файлов в родительской директории. Если родительская директория пуста, метод возвращает null.
        File[] filesInDirectory = parentDirectory.listFiles();

        // Если массив файлов не равен null, то выполняется блок кода. Это означает, что в родительской директории есть файлы.
        if (filesInDirectory != null) {

        // Создается список fileNames для хранения имен файлов.
        List<String> fileNames = new ArrayList<>();

        // Используется цикл for для перебора всех файлов в родительской директории.
        for (File file : filesInDirectory) {

        // Если файл является файлом (а не директорией), его имя добавляется в список fileNames.
        if (file.isFile()) {
        fileNames.add(file.getName());
        }
    }

        // Создается список fileList, который является ObservableList. ObservableList - это специальный тип списка в JavaFX, который позволяет автоматически обновлять пользовательский интерфейс при изменении списка.
        ObservableList<String> fileList = FXCollections.observableArrayList(fileNames);

        // Метод setItems() устанавливает список элементов для ListView. ListView - это компонент пользовательского интерфейса в JavaFX, который отображает список элементов.
        listView.setItems(fileList);
        }

        // Выводится сообщение "Выбранный файл: " и абсолютный путь к выбранному файлу в консоль.
        System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
        } else {
        // Если выбранный файл равен null, то выполняется блок кода. Это означает, что пользователь отменил выбор файла.

        // Создается объект Alert с типом Alert.AlertType.INFORMATION. Alert - это класс в JavaFX, который используется для отображения сообщений пользователю.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выбор файла отменен");

        // Метод setHeaderText() устанавливает заголовок для Alert.
        alert.setHeaderText("Внимание");

        // Метод showAndWait() отображает Alert и ожидает, пока пользователь не закроет его.
        alert.showAndWait();

        // Выводится сообщение "Выбор файла отменен" в консоль.
        System.out.println("Выбор файла отменен");
        }
         */
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выбор файла отменен");
            alert.setHeaderText("Внимание");
            alert.showAndWait();

            System.out.println("Выбор файла отменен");
        }


        /*

        // Метод setTitle() устанавливает заголовок диалогового окна выбора директории. В данном случае, заголовок установлен как "Выберите файл".
        directoryChooser.setTitle("Выберите файл");

        // Создается объект Stage с именем directoryStage. Stage - это класс в JavaFX, который представляет окно в пользовательском интерфейсе.
        Stage directoryStage = new Stage();

        // Метод showDialog() отображает диалоговое окно выбора директории. Если пользователь выбирает директорию, метод возвращает объект File, представляющий выбранную директорию. Если пользователь отменяет выбор директории, метод возвращает null.
        File chooseFileCopy = directoryChooser.showDialog(directoryStage);

        // Если выбранная директория не равна null, то выполняется блок кода. Это означает, что пользователь выбрал директорию.
        if (chooseFileCopy != null) {

        // Метод getAbsolutePath() возвращает абсолютный путь к выбранной директории. Этот путь затем устанавливается в текстовое поле pathFolder.
        pathFolder.setText(chooseFileCopy.getAbsolutePath());

        // Выводится сообщение "Выбранный файл: " и абсолютный путь к выбранной директории в консоль.
        System.out.println("Выбранный файл: " + chooseFileCopy.getAbsolutePath());
    } else {
        // Если выбранная директория равна null, то выполняется блок кода. Это означает, что пользователь отменил выбор директории.

        // Создается объект Alert с типом Alert.AlertType.INFORMATION. Alert - это класс в JavaFX, который используется для отображения сообщений пользователю.
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выбор файла отменен");

        // Метод setHeaderText() устанавливает заголовок для Alert.
        alert.setHeaderText("Внимание");

        // Метод showAndWait() отображает Alert и ожидает, пока пользователь не закроет его.
        alert.showAndWait();

        // Выводится сообщение "Выбор файла отменен" в консоль.
        System.out.println("Выбор файла отменен");
    }
         */
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
     * Запуск автоматизированного скрипта с периодичностью по времени
     */
    public void handleAutoStartScript(ActionEvent actionEvent) {
        if (selectDateTime.getLocalDateTime() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали дату или время");
            alert.setHeaderText("Внимание");
            alert.showAndWait();
        } else {
            String cronDateTime = convertToCronExpression(selectDateTime.getLocalDateTime());
            System.out.println(cronDateTime);

            if (pathFolder.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы не выбрали путь к директории");
                alert.setHeaderText("Внимание");
                alert.showAndWait();
            } else {

                String command2  = "echo \"Резервная копия создана $(date)\" >> backup_report.txt";

                // String commadn3 = "du -sh /path/to/backup_directory >> backup_report.txt";
                // String command = "sudo tar -cvzf /"+ pathFolder.getText() + "backup_$(date +%F).tar.gz /etc";

                String tarCommand = "tar -zcvf " + pathFolder.getText() + "-`date '+%F'`.tar.gz /etc";

                // + Отчет или же альтернатива готовый bash скрипт
                // TODO Добавить в command еще команду для формирования отчета:  + echo "Backup created on $(date)" >> backup_report.txt

                String crontabAndTarCommand = "sudo echo " + cronDateTime + " " + tarCommand + " " + "| crontab -\n";

                String commadn3 = "du -sh /path/to/backup_directory >> backup " + cronDateTime + " report.txt";


                /* Или можно запистаь так с помощью нескольких команд и через запятую передать из в ProcessBuilder()

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

                try {
                    ProcessBuilder processBuilderBASH = new ProcessBuilder("bash", "-c", crontabAndTarCommand);
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

    /**
     * Переименование файла
     */
    public void handleRenameFile(ActionEvent actionEvent) {

//
//        String filePath = nameBackupCopy.getText();
//
//
////        String newName = nameBackupCopy.getText();
////        File newFile = new File(oldFile.getParent(), newName);
//
//        if (!filePath.isEmpty()) {
//            File fileToDelete = new File(filePath);
//
//            if (fileToDelete.exists()) {
//                //if (fileToDelete.renameTo()) {
//                    System.out.println("Файл удален: " + filePath);
//                    nameBackupCopy.clear();
//                } else {
//                    System.out.println("Не удалось удалить файл: " + filePath);
//                }
//            } else {
//                System.out.println("Файл не существует: " + filePath);
//            }
//        } else {
//            System.out.println("Введите путь к файлу.");
//        }
//







//
//        boolean succes = false;
//
//        String oldFileName = nameBackupCopy.getText();
//        File oldFile = new File(oldFileName);
//        String filePath = oldFile.getParent();
//
//        String fileName = oldFile.getName();
//        if (buttonClicked) {
//        nameBackupCopy.setEditable(false);
//
//        }
////        renameFile
////
////        if (nameBackupCopy.getText() != null) {
////            String newName = nameBackupCopy.getText();
////            File newFile = new File(oldFile.getParent(), newName);
////
////            if (oldFile.renameTo(newFile)) {
////                System.out.println("Файл успешно переименован в: " + newFile.getAbsolutePath());
////            } else {
////                System.out.println("Не удалось переименовать файл.");
////            }
////        }
////
////        // Создание текстового поля для ввода нового имени файла
////
////        TextField oldFileNameTextField = new TextField();
////        // Создание кнопки для запуска процесса переименования файла
////        Button renameButton = new Button("Переименовать файл");
//
//        else {
//            nameBackupCopy.setEditable(true);
//            String newFileName = nameBackupCopy.getText();
//            File newFile = new File(newFileName);
//            oldFile.renameTo(newFile);
//            buttonClicked = true;
//            nameBackupCopy.setEditable(false);
//        }

//            if (succes) {
//
//                nameBackupCopy.setEditable(false);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Файл успешно переименован.");
//                alert.setHeaderText("Внимание");
//                alert.showAndWait();
//                System.out.println("Файл успешно переименован.");
//
//            } else {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Не удалось переименовать файл.");
//                alert.setHeaderText("Внимание");
//                alert.showAndWait();
//                System.out.println("Не удалось переименовать файл.");
//                nameBackupCopy.setEditable(false);
//            }


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
