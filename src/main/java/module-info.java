module org.example.taller3 {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.taller3.vista;
    exports org.example.taller3.modelo;
    exports org.example.taller3.controlador;

    opens org.example.taller3.vista to javafx.fxml;
}
