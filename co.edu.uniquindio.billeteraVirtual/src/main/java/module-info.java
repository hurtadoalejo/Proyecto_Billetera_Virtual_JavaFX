module co.edu.uniquindio.billeteravirtual.billeteravirtualapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.unsupported.desktop;


    opens co.edu.uniquindio.billeteravirtual.billeteravirtualapp to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtualapp;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

    opens co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller;
}