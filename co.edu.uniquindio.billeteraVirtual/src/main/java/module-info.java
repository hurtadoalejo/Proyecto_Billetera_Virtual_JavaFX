module co.edu.uniquindio.billeteravirtual.billeteravirtualapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.billeteravirtual.billeteravirtualapp to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtualapp;
}