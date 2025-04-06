package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.App;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuUsuarioController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginMenuUsuarioViewController {

    LoginMenuUsuarioController loginMenuUsuarioController;

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
    private Label lb_id;

    @FXML
    private TextField ta_id;

    @FXML
    private Label lb_clave;

    @FXML
    void onIngresar() {
        ingresar();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void ingresar() {
        if (verificarCambosLlenos() && verificarCambosCorrectos()){
            loginMenuUsuarioController.verificarCredencialesUsuario(ta_id.getText(), Integer.parseInt(ta_clave.getText()));

        }
    }

    private boolean verificarCambosLlenos(){
        if (ta_clave.getText().isEmpty() || ta_id.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCambosCorrectos(){
        if (isInteger(ta_clave.getText()) && isInteger(ta_id.getText())) {
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
        loginMenuUsuarioController = new LoginMenuUsuarioController();
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ap_principal != null : "fx:id=\"ap_principal\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ta_clave != null : "fx:id=\"ta_clave\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert bt_ingresar != null : "fx:id=\"bt_ingresar\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert lb_id != null : "fx:id=\"lb_id\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ta_id != null : "fx:id=\"ta_id\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert lb_clave != null : "fx:id=\"lb_clave\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";

    }
}