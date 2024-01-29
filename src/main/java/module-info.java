module com.example.demo2 {
    requires jfxtras.controls;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}