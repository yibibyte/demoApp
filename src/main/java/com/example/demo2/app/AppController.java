package com.example.demo2.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;


/**
 * @author G.I.V.
 * класс AppController Контроллер для связвания UI, то есть нашей заготовки app.fxml с JavaFX элементами(компонентами) в java
 * fx:id в app.fxml должен быть равен названию перемной Элемента (к примеру экземпляру Button)
 * Так же класс Контроллер служит для обработки собитий по нажатию этих элементов
 * @since {@code }
 */
public class AppController implements Initializable {

    // Одна из всех переменная объявленная как static , то есть это строка в глобальной области видимости, то есть она одна на всех и ее видно всем ниже в коде, так как она объявлена здесь
    static String currentNameFile;
    /**
     * Start Script Copy
     */
    @FXML
    public Button startScriptCopy;
    private Stage stage;
    /**
     * Rename of File
     */
    @FXML
    private Button renameFile;
    @FXML
    private MenuItem menuExit;
    @FXML
    private MenuItem menuDelete;
    @FXML
    private MenuItem menuOpenDirectory;
    @FXML
    private MenuItem menuJavaDoc;
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
    private Label lablePathBackupFile;
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

    /**
     * @param stage Фундамент для сцены
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Метод initialize инициализирует при старте приложения по умолчанию какими-то значениями наши объект (компоненты) в нашем случи это время в LocalDateTimePicker, где LocalDateTime.now() получаем текущее актуально время и дату
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


    @FXML
    void handleMenuDelete(ActionEvent event) {
        this.handleDeleteFileCopy(event);
    }

    @FXML
    void handleMenuExit(ActionEvent event) {

        // 1 вариант Выход с подтверждением
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Выход из приложения");
//        alert.setHeaderText("Вы действительно хотите выйти?");
//        alert.setContentText("Все несохраненные данные будут потеряны.");
//
//        ButtonType buttonTypeOk = new ButtonType("Да");
//        ButtonType buttonTypeCancel = new ButtonType("Нет");
//
//        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
//
//        Optional<ButtonType> result = alert.showAndWait();
//
//        if (result.get() == buttonTypeOk) {
//            System.exit(0);
//        }

        // 2 вариант Выход без подтверждениемБез подтверждения

        System.exit(0);

    }


    @FXML
    void handleMenuJavaDoc(ActionEvent event) {
        try {
            // Выполняем команду Windows. Либо вот такая строка то есть абсолютный путь String filePath = "E:\\Java\\telegram\\demo2\\index.html";
            // ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "explorer.exe", "index.html");

            ProcessBuilder processBuilder = new ProcessBuilder("explorer.exe", "index.html");

             /*
             Формируем команду для открытия файла в Linux
             xdg-open --version
             sudo apt install xdg-open
             Выполняем команду Linux
             ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "xdg-open", "index.html");
             ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", "index.html");
             */

            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleMenuOpenDirectory(ActionEvent event) {
        this.handleChooseDirectory(event);
    }

    @SuppressWarnings("javadoc")
    public void handleSelectDateTime(MouseEvent mouseEvent) {
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
     *
     * @param dateTime передаем в наш метод, и это параметр это объект нашего календаря типа LocalDateTimePicker,
     *                 который отдает нам LocalDateTime dateTime, где мы его уже переводим в int для работы подстановки в cron
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

        return minute + " " + hour + " " + dayOfMonth + " " + month + " " + dayOfWeek;
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
            nameBackupCopy.setText(selectedFile.getName());

            currentNameFile = nameBackupCopy.getText();

            // Указываем путь к файлу в Label
            lablePathBackupFile.setText(selectedFile.getParent());

            // Здесь мы получаем путь к родительской директории из выбранного нашего файла, для того,
            // чтобы потом посмотреть какие еще файлы такого типа лежат по этому пути
            File parentDirectory = selectedFile.getParentFile();

            // Получаем массив всех файлов из этой директории
            File[] filesInDirectory = parentDirectory.listFiles();

            // Проверяем что наш список не оказался пустым
            if (filesInDirectory != null) {

                // Получаем список имен файлов из этого массива
                List<String> fileNames = new ArrayList<>();
                for (File file : filesInDirectory) {
                    // Делаем проверку что это Файл, а не какая-то папка и чтобы этот файл был с расширением tar.gz
                    if (file.isFile() && file.getName().endsWith(".tar.gz")) {
                        // добавляем этот массив нужных нам файлов в список
                        fileNames.add(file.getName());
                    }
                }

                // Обновляем ListView с помощью Наблюдаемого Списка, который следит за актуализацией данных
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
        String filePath = lablePathBackupFile.getText() + "/" + nameBackupCopy.getText();

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

                String command2 = "echo \"Резервная копия создана $(date)\" >> backup_report.txt";

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
        // Обработчик событий handleRenameFile(ActionEvent actionEvent) переименовывает старый файл на новый,
        // где имя старого файла сохраняется в глобальной переменной actualNameTextFieldBackupFile, которая объявлена выше для видимости во всем коде

        // Берем путь к файлу из Label для того, чтобы установить ему потом к новому переименованному файл
        String pathDirectoryFile = lablePathBackupFile.getText();

        // Вытаскиваем имя нового файла из TextFiled, который изменил пользователь
        String newFileName = nameBackupCopy.getText();

        // Если имя нового файла не пустой
        if (!newFileName.isEmpty()) {

            // Получаем текущий файл, где создаем объект типа File currentFile инициализируем его тем путем,
            // который у нас есть, и старым именем, который у нас был, чтобы потом его поменять на новый
            File currentFile = new File(pathDirectoryFile, currentNameFile);

            // Проверяем, существует ли файл в папке файл, то есть убеждаемся соответствует ли созданный объект File currentFile в файловой системе
            if (currentFile.exists()) {

                // Создаем новый файл с измененным именем в той же директории
                File newFile = new File(pathDirectoryFile, newFileName);

                try {
                    FileUtils.moveFile(currentFile, newFile);

                    //Files.move(currentPath, newPath, StandardCopyOption.REPLACE_EXISTING);
                    currentNameFile = newFileName;

                    // Временно создаем временный объект new File(pathDirectoryFile) подставив путь как параметр, чтобы использовать только локально,
                    // для того чтобы указать путь к директории где хранятся с нашим расширением *.tar.gz файлы

                    Collection<File> files = FileUtils.listFiles(new File(pathDirectoryFile), new WildcardFileFilter("*.tar.gz"), null);

                    // Теперь у нас есть список имен файлов с расширением .tar.gz в указанной директории
                    List<String> fileNames = new ArrayList<>();
                    for (File f : files) {
                        fileNames.add(f.getName());
                    }

                    // Обновляем ListView
                    ObservableList<String> fileList = FXCollections.observableArrayList(fileNames);
                    listView.setItems(fileList);


                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Файл успешно переименован");
                    alert.setHeaderText("Внимание");
                    alert.showAndWait();
                    System.out.println("Файл успешно переименован.");


                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Файл не существует или это тот же файл");
                    alert.setHeaderText("Внимание");
                    alert.showAndWait();

                    System.out.println("Файл не существует или это тот же файл.");
                }

            } else {  // а здесь выдаем сообщение на, что не существует такого файла,
                // когда мы создавали объект File currentFile и проверили его существование методов exists()
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Файл не существует или это тот же файл, введите корректное имя файла с расширением, или выберите резервную копию");
                alert.setHeaderText("Внимание");
                alert.showAndWait();

                System.out.println("Файл не существует или это тот же файл, выберите резервную копию, или введите корректное имя файла с расширением.");
            }
        } else { // а здесь мы проверяем имя нового файла, оно не должно быть пустым, то есть пользователь может вообще оставить поле пустым, тогда мы его предупредим
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Введите имя файла");
            alert.setHeaderText("Внимание");
            alert.showAndWait();

            System.out.println("Введите новое имя файла.");
        }
    }

    /**
     * Запуск резервного копирования в текущий момент
     */
    public void handleStartScriptCopy(ActionEvent actionEvent) {

        String pathToFile = lablePathBackupFile.getText() + "/" + nameBackupCopy.getText();
        if (pathToFile.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Выберите корректный файл для запуска");
            alert.setHeaderText("Внимание");
            alert.showAndWait();
        } else {
            // Скрипт который можно взять для запуска ранее созданными резервное копирование
            String commandBashScript = "bash backup.sh";
               /*
                cd /
                sudo tar -zcvpf /backup-`date '+%F'`.tar.gz --directory / --exclude=proc --exclude=var --exclude=mnt --exclude=usr --exclude=backup .

                sudo tar -zcvf /etc-`date '+%F'`.tar.gz /etc

                Восстановление
                cd
                tar -zxpf /etc-2024-02-08/tar.gz

                Опция "р" сохраняет права доступа.
                Опция "f" указывает на то, что следующим аргументом является имя архива или устройства
                */
            String commandRecoverBackup = "tar -zxpf " + pathToFile;

            String commanEcho = "echo \"Резервная копия создана $(date)\" >> " + nameBackupCopy.getText() + "/\"backup report $(date)\".txt";
            String commanDu = "du -sh " + nameBackupCopy.getText() + "/ >> \"backup report $(date)\".txt";
            try {
                ProcessBuilder processBuilderBASH = new ProcessBuilder("bash", "-c", commandRecoverBackup);
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
