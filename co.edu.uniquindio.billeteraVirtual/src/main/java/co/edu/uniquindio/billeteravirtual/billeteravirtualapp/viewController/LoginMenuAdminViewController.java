package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.App;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuAdminController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginMenuAdminViewController {

    private LoginMenuAdminController loginMenuAdminController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_titulo;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private TextField ta_clave;

    @FXML
    private Button bt_ingresar;

    @FXML
    private Label lb_ingresar;

    @FXML
    void onIngresar() {
        ingresar();
    }

    private void ingresar() {
        if (verificarCambosCorrectos() && verificarCambosLlenos()){
            loginMenuAdminController.verificarClaveAdmin(Integer.parseInt(ta_clave.getText()));
        }
    }

    private boolean verificarCambosLlenos(){
        if (ta_clave.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCambosCorrectos(){
        if (isInteger(ta_clave.getText())) {
            return true;
        }
        return false;
    }

    private boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    void initialize() {
        loginMenuAdminController = new LoginMenuAdminController();
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert ap_principal != null : "fx:id=\"ap_principal\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert ta_clave != null : "fx:id=\"ta_clave\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert bt_ingresar != null : "fx:id=\"bt_ingresar\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert lb_ingresar != null : "fx:id=\"lb_ingresar\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";

    }
}