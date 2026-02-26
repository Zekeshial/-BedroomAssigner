module com.example.lab4project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab4project to javafx.fxml;
    exports com.example.lab4project;
}