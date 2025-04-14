package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MenuAdminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap_menuAdmin;

    @FXML
    private TabPane tp_menuAdmin;

    @FXML
    private Tab tab_gestionUsuarios;

    @FXML
    private SplitPane sp_gestionUsuarios;

    @FXML
    private Tab tab_gestionCuentas;

    @FXML
    private SplitPane sp_gestionCuentas;

    @FXML
    private Tab tab_estadisticas;

    @FXML
    private Tab tab_graficas;

    @FXML
    private Tab tab_gestionTransacciones;

    /**
     * Metodo para configurar el cambio de tab
     */
    private void configurarCambioDeTab() {
        tp_menuAdmin.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_gestionUsuarios) {
                cargarVistaGestionUsuarios();
            } else if (newTab == tab_gestionCuentas) {
                cargarVistaGestionCuentas();
            } else if (newTab == tab_estadisticas) {
                System.out.println();
            } else if (newTab == tab_graficas) {
                System.out.println();
            } else if (newTab == tab_gestionTransacciones) {
                System.out.println();
            }
        });
    }

    /**
     * Metodo para cargar la vista de la gestion de usuario
     */
    private void cargarVistaGestionUsuarios() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionUsuarios.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_gestionUsuarios.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar la vista de la gestion de cuentas
     */
    private void cargarVistaGestionCuentas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionCuentas.fxml"));
            AnchorPane nuevaVista = loader.load();
            sp_gestionCuentas.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para inicializar el MenuAdminViewController
     */
    @FXML
    void initialize() {
        configurarCambioDeTab();
        assert ap_menuAdmin != null : "fx:id=\"ap_menuAdmin\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tp_menuAdmin != null : "fx:id=\"tp_menuAdmin\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionUsuarios != null : "fx:id=\"tab_gestionUsuarios\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert sp_gestionUsuarios != null : "fx:id=\"sp_gestionUsuarios\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionCuentas != null : "fx:id=\"tab_gestionCuentas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert sp_gestionCuentas != null : "fx:id=\"sp_gestionCuentas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_estadisticas != null : "fx:id=\"tab_estadisticas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_graficas != null : "fx:id=\"tab_graficas\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
        assert tab_gestionTransacciones != null : "fx:id=\"tab_gestionTransacciones\" was not injected: check your FXML file 'MenuAdmin.fxml'.";
    }
}