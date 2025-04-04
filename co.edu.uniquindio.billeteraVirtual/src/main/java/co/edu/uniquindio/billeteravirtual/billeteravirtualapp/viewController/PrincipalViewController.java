package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.PrincipalController;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PrincipalViewController {

    PrincipalController principalController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap_admin;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private AnchorPane ap_usuario;

    @FXML
    private TabPane tp_principal;

    @FXML
    private Tab tab_admin;

    @FXML
    private SplitPane sp_admin;

    @FXML
    private SplitPane sp_usuario;

    @FXML
    private Tab tab_usuario;

    @FXML
    void initialize() {
        principalController = new PrincipalController();
        assert ap_admin != null : "fx:id=\"ap_admin\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert ap_principal != null : "fx:id=\"ap_principal\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert tp_principal != null : "fx:id=\"tp_principal\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert ap_usuario != null : "fx:id=\"ap_usuario\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert tab_admin != null : "fx:id=\"tab_admin\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert sp_admin != null : "fx:id=\"sp_admin\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert sp_usuario != null : "fx:id=\"sp_usuario\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert tab_usuario != null : "fx:id=\"tab_usuario\" was not injected: check your FXML file 'PrincipalView.fxml'.";
    }
}