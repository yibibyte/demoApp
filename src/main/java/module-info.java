/**
 * Описание модуля com.example.demo2.
 * Дополнительная информация о лицензировании.
 */
module com.example.demo2 {
    requires jfxtras.controls; // Зависимость от модуля jfxtras.controls

    opens com.example.demo2 to javafx.fxml; // Открытие пакета com.example.demo2 для использования в JavaFX FXML
    exports com.example.demo2; // Экспорт пакета com.example.demo2
    exports com.example.demo2.app; // Экспорт пакета com.example.demo2.app
    opens com.example.demo2.app to javafx.fxml; // Открытие пакета com.example.demo2.app для использования в JavaFX FXML
}