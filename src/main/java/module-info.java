module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxtras.controls;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}