package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.MenuAdminController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class MenuAdminViewController {

    private MenuAdminController menuAdminController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap_menuAdmin;

    @FXML
    private Tab tab_gestionUsuarios;

    @FXML
    private Tab tab_gestionCuentas;

    @FXML
    private Tab tab_estadisticas;

    @FXML
    private Tab tab_graficas;

    @FXML
    private Tab tab_gestionTransacciones;

    @FXML
    void initialize() {
        menuAdminController= new MenuAdminController();
        assert ap_menuAdmin != null : "fx:id=\"ap_menuAdmin\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionUsuarios != null : "fx:id=\"tab_gestionUsuarios\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionCuentas != null : "fx:id=\"tab_gestionCuentas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_estadisticas != null : "fx:id=\"tab_estadisticas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_graficas != null : "fx:id=\"tab_graficas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionTransacciones != null : "fx:id=\"tab_gestionTransacciones\" was not injected: check your FXML file 'MenuAdmin.fxml'.";

    }
}